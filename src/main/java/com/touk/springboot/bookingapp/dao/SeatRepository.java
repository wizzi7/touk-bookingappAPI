package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
