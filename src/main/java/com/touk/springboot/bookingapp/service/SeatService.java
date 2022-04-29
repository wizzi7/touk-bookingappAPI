package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Seat;

public interface SeatService {
	
	public Seat findById(int seatId);

	public List<Seat> findAll();
	
	public Seat save(Seat seat);

}
