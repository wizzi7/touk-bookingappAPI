package com.touk.springboot.bookingapp.service;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.touk.springboot.bookingapp.dao.ReservationRepository;
import com.touk.springboot.bookingapp.entity.Client;
import com.touk.springboot.bookingapp.entity.Reservation;
import com.touk.springboot.bookingapp.entity.Row;
import com.touk.springboot.bookingapp.entity.Screening;
import com.touk.springboot.bookingapp.entity.Seat;
import com.touk.springboot.bookingapp.entity.Ticket;
import com.touk.springboot.bookingapp.jsonview.ReservationViews;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ScreeningService screeningService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private RowService rowService;
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String findById(int reservationId) {
		Optional<Reservation> result = reservationRepository.findById(reservationId);
		Reservation reservation = null;
		if (result.isPresent()) {
			reservation = result.get();
		}
		else {
			throw new RuntimeException("Reservation id not found - " + reservationId);
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String res = null;
	     try {
	    	 res = mapper
			  .writerWithView(ReservationViews.Normal.class)
			  .writeValueAsString(reservation);
		 } catch (JsonProcessingException e) {
		    	e.printStackTrace();
		 }
	     return res;
	}
	
	@Override
	public Reservation save(Reservation reservation) {
		Reservation newReservation = new Reservation();
		newReservation.setScreening(screeningService.findById(reservation.getScreening().getScreeningId()));
		return reservationRepository.save(newReservation);
	}
	
	private boolean canBeBookedLeftSide(Seat seat) {
		Seat neighbour = seatService.findById(seat.getSeatId() + 2);
		if (neighbour.isOccupied()) {
			Seat neighbour2 = seatService.findById(seat.getSeatId() + 1);
			if (neighbour2.isOccupied()) {
				return true;
			}
			else
				return false;
		}
		else {
			return true;
		}
	}
	
	private boolean canBeBookedRightSide(Seat seat) {
		Seat neighbour = seatService.findById(seat.getSeatId() - 2);
		if (neighbour.isOccupied()) {
			Seat neighbour2 = seatService.findById(seat.getSeatId() - 1);
			if (neighbour2.isOccupied()) {
				return true;
			}
			else
				return false;
		}
		else {
			return true;
		}
	}
	
	private boolean canBeBookedCenter(Seat seat) {
		Seat neighbour = seatService.findById(seat.getSeatId() - 2);
		Seat neighbour2 = seatService.findById(seat.getSeatId() + 2);
		if (neighbour.isOccupied()) {
			neighbour = seatService.findById(seat.getSeatId() - 1);
			if (!neighbour.isOccupied())
				return false;
		}
		if (neighbour2.isOccupied()) {
			neighbour2 = seatService.findById(seat.getSeatId() + 1);
			if (!neighbour.isOccupied())
				return false;
		}
		return true;
	}
	
	private boolean canBeBooked(Seat seat) {
	    Row row = rowService.findById(seat.getRow().getRowId());
	    int  rowLength = row.getAvailableSeats();
	    if (seat.getNumber() == 1 || seat.getNumber() == 2) {
			if (canBeBookedLeftSide(seat)) {
				seat.setIsOccupied(true);
				seatService.save(seat);
				return true;
			}
			else
				return false;
		}
	    else if (seat.getNumber() == rowLength || seat.getNumber() == rowLength - 1) {
	    	if (canBeBookedRightSide(seat)) {
	    		seat.setIsOccupied(true);
				seatService.save(seat);
				return true;
	    	}
			else
				return false;
		}
	    else {
	    	if (canBeBookedCenter(seat)) {
	    		seat.setIsOccupied(true);
				seatService.save(seat);
				return true;
	    	}
	    	else
	    		return false;
	    }
	}

	public String confirmReservation(Reservation reservation){
		try {
			Client client = reservation.getClient();
			if (!isClientDataValid(client)) {
				JSONObject obj = new JSONObject();
				String error = "Wrong buyer data has been entered";
				obj.put("error", error);
				return obj.toString(2);
			}
			List<Seat> seats = reservation.getSeats();
			int seatCount = seats.size();
			if (seatCount == 1) {
				Seat seat = seatService.findById(seats.get(0).getSeatId());
				if (seat.isOccupied()) {
					JSONObject obj = new JSONObject();
					Row row = rowService.findById(seat.getRow().getRowId());
					String error = "Seat " + row.getName() + seat.getNumber() + " already booked";
					obj.put("error", error);
					return obj.toString(2);
				}
				if (canBeBooked(seat)) {
					return createFinalReservation(seats, reservation);
				}
				else
					return "This seat cannot be booked";
			}
			else {
				List<Seat> seatsSorted = seats.stream().sorted(Comparator.comparing(seat -> seat.getSeatId())).collect(Collectors.toList());
				Seat leftSeat = seatService.findById(seatsSorted.get(0).getSeatId());
				Seat rightSeat = seatService.findById(seatsSorted.get(seatsSorted.size()-1).getSeatId());
				int leftSeatId = leftSeat.getSeatId();
				int rightSeatId = rightSeat.getSeatId();
				Row row = rowService.findById(rightSeat.getRow().getRowId());
			    int  rowLength = row.getAvailableSeats();
				boolean finalizeBooking = true;
				if (leftSeatId > 2)
					finalizeBooking = true == canBeBookedRightSide(leftSeat);
				if (rightSeatId < rowLength - 2)
					finalizeBooking = true == canBeBookedLeftSide(leftSeat);
				if (finalizeBooking) {
					for (Seat seat : seatsSorted) {
						Seat resSeat = seatService.findById(seat.getSeatId());
						if (resSeat.isOccupied()) {
							JSONObject obj = new JSONObject();
							String error = "Seat " + row.getName() + resSeat.getNumber() + " already booked";
							obj.put("error", error);
							return obj.toString(2);
						}
						resSeat.setIsOccupied(true);
						seatService.save(resSeat);
					}
					return createFinalReservation(seats, reservation);
				}
				else
					return "These seats cannot be booked";
			}
		}
		catch (RuntimeException e) {
			JSONObject obj = new JSONObject();
			String error = "A non-existing seat or ticket was selected";
			obj.put("error", error);
			return obj.toString(2);
		}
	}
	
	private String createFinalReservation(List<Seat> seats, Reservation reservation) {
		double totalCost = 0;
		for (Seat seat : seats) {
			Ticket ticket = ticketService.findById(seat.getTicket().getTicketId());
			totalCost += ticket.getPrice();
		}
		Screening screening = screeningService.findById(reservation.getScreening().getScreeningId());
		JSONObject obj = new JSONObject();
		obj.put("total cost", totalCost);
		obj.put("expiration time", screening.getStart().minusMinutes(45).format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))); //tickets have to be paid at least 45 minute before screening begins
		return obj.toString(2);
	}
	
	private boolean isClientDataValid(Client client) {
		String name = client.getName();
		String surname = client.getSurname();
		if (name.length() < 3 || !name.matches("[a-zA-ZąęćśżźółĆŚŻŹŁÓ]+") || !Character.isUpperCase(name.charAt(0)))
			return false;
		if(surname.length() < 3 || !surname.matches("[a-zA-Z-ąęćśżźółĆŚŻŹŁÓ]+") || !Character.isUpperCase(surname.charAt(0)))
			return false;
		else if (surname.contains("-")) {
			if (!Character.isUpperCase(surname.split("-")[1].charAt(0)))
				return false;
		}
		return true;
	}
}
