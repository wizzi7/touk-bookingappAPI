package com.touk.springboot.bookingapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.touk.springboot.bookingapp.entity.Screening;

public interface ScreeningService {

	public Screening findById(int screeningId);

	public List<Screening> findAll();
	
	public String findAllByDateAndTime(LocalDateTime startDateTime);

}
