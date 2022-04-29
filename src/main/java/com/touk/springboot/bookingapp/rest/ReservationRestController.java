package com.touk.springboot.bookingapp.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.touk.springboot.bookingapp.entity.Reservation;
import com.touk.springboot.bookingapp.entity.Screening;
import com.touk.springboot.bookingapp.service.ClientService;
import com.touk.springboot.bookingapp.service.ReservationService;
import com.touk.springboot.bookingapp.service.ScreeningService;

@RestController
@RequestMapping("/api")
public class ReservationRestController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ScreeningService screeningService;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/reservations")
	public List<Reservation> findAll(){
		return reservationService.findAll();
	}
	
	@GetMapping("/reservations/{reservationId}")
	public String getReservation(@PathVariable int reservationId){
		String reservation = reservationService.findById(reservationId);
		if (reservation == null) {
			throw new RuntimeException("Reservation id not found - " + reservationId);
		}
		return reservation;
	}
	
	@RequestMapping(value ="/reservations", method = RequestMethod.POST,  produces = "application/json")
	public ResponseEntity<?> confirmReservation(@RequestBody Reservation reservation){
		Screening screening = null;
		try {
			screening = screeningService.findById(reservation.getScreening().getScreeningId());
		} catch (RuntimeException e) {
			JSONObject obj = new JSONObject();
			String error = "A non-existing screening was selected";
			obj.put("error", error);
			return new ResponseEntity<>(obj.toString(2), HttpStatus.BAD_REQUEST);
		}
		LocalDateTime currentTime = LocalDateTime.now();
		if (screening.getStart().minusMinutes(15).isAfter(currentTime)) {
			String res = reservationService.confirmReservation(reservation);
			if (res.startsWith("{\"error\": \"Seat") || res.startsWith("{\"error\": \"Wrong") || res.startsWith("{\"error\": \"A non")) {
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			Reservation newReservation = reservationService.save(reservation);
			clientService.save(reservation.getClient(), newReservation);
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		}
		else {
			JSONObject obj = new JSONObject();
			String error = "Unable to book tickets - reservation time has expired";
			obj.put("error", error);
			return new ResponseEntity<String>(obj.toString(2), HttpStatus.BAD_REQUEST); 
		}
	}
}
