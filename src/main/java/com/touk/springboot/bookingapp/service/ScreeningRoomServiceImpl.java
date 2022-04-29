package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.touk.springboot.bookingapp.dao.ScreeningRoomRepository;
import com.touk.springboot.bookingapp.entity.ScreeningRoom;
import com.touk.springboot.bookingapp.jsonview.ScreeningRoomViews;

@Service
public class ScreeningRoomServiceImpl implements ScreeningRoomService {
	
	@Autowired
	private ScreeningRoomRepository screeningRoomRepository;

	@Override
	public List<ScreeningRoom> findAll() {
		return screeningRoomRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String findById(int screeningRoomId) {
		Optional<ScreeningRoom> result = screeningRoomRepository.findById(screeningRoomId);
		ScreeningRoom screeningRoom = null;
		if (result.isPresent()) {
			screeningRoom = result.get();
		}
		else {
			throw new RuntimeException("ScreeningRoom id not found - " + screeningRoomId);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String res = null;
	     try {
	    	 res = mapper
			  .writerWithView(ScreeningRoomViews.Normal.class)
			  .writeValueAsString(screeningRoom);
		 } catch (JsonProcessingException e) {
		    	e.printStackTrace();
		 }
	     return res;
	}
}
