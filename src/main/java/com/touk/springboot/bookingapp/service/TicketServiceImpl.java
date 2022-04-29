package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touk.springboot.bookingapp.dao.TicketRepository;
import com.touk.springboot.bookingapp.entity.Ticket;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findById(int ticketId) {
		Optional<Ticket> result = ticketRepository.findById(ticketId);
		Ticket ticket = null;
		if (result.isPresent()) {
			ticket = result.get();
		}
		else {
			throw new RuntimeException("Ticket id not found - " + ticketId);
		}
		return ticket;
	}
}
