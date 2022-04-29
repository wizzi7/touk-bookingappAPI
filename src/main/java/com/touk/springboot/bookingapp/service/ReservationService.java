package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Reservation;

public interface ReservationService {

	public List<Reservation> findAll();

	public String findById(int reservationId);
	
	public String confirmReservation(Reservation reservation);
	
	public Reservation save(Reservation reservation);

}
