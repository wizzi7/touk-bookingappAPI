package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
