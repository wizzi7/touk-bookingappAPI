package com.touk.springboot.bookingapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.touk.springboot.bookingapp.entity.ScreeningRoom;
import com.touk.springboot.bookingapp.service.ScreeningRoomService;

@RestController
@RequestMapping("/api")
public class ScreeningRoomRestController {
	
	@Autowired
	private ScreeningRoomService screeningRoomService;

	@GetMapping("/screeningRooms")
	public List<ScreeningRoom> findAll(){
		return screeningRoomService.findAll();
	}
	
	@RequestMapping(value="/screeningRooms/{screeningRoomId}", method=RequestMethod.GET, produces="application/json")
	public String getScreeningRoom(@PathVariable int screeningRoomId){
		String screeningRoom = screeningRoomService.findById(screeningRoomId);
		if (screeningRoom == null) {
			throw new RuntimeException("ScreeningRoom id not found - " + screeningRoomId);
		}
		return screeningRoom;
	}
}
