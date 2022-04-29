package com.touk.springboot.bookingapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.touk.springboot.bookingapp.entity.Seat;
import com.touk.springboot.bookingapp.service.SeatService;

@RestController
@RequestMapping("/api")
public class SeatRestController {
	
	@Autowired
	private SeatService seatService;
	
	@GetMapping("/seats")
	public List<Seat> findAll(){
		return seatService.findAll();
	}
	
	@GetMapping("/seats/{seatId}")
	public Seat getSeat(@PathVariable int seatId){
		Seat seat = seatService.findById(seatId);
		if (seat == null) {
			throw new RuntimeException("Seat id not found - " + seatId);
		}
		return seat;
	}
}
