package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Ticket;

public interface TicketService{

	public List<Ticket> findAll();

	public Ticket findById(int ticketId);

}
