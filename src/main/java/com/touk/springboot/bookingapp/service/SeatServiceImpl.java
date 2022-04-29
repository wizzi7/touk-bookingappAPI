package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touk.springboot.bookingapp.dao.SeatRepository;
import com.touk.springboot.bookingapp.entity.Seat;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public List<Seat> findAll() {
		return seatRepository.findAll();
	}

	@Override
	public Seat findById(int seatId) {
		Optional<Seat> result = seatRepository.findById(seatId);
		Seat seat = null;
		if (result.isPresent()) {
			seat = result.get();
		}
		else {
			throw new RuntimeException("Seat id not found - " + seatId);
		}
		return seat;
	}

	@Override
	public Seat save(Seat seat) {
		return seatRepository.save(seat);
	}
}
