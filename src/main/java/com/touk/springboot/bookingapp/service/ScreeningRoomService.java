package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.ScreeningRoom;

public interface ScreeningRoomService {

	public List<ScreeningRoom> findAll();
	
	public String findById(int screeningRoomid);
	
}
