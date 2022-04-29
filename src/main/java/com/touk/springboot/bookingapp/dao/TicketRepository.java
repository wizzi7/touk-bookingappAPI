package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
