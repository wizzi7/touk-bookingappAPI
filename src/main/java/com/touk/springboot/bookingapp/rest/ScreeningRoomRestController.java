package com.touk.springboot.bookingapp.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getScreeningRoom(@PathVariable int screeningRoomId){
		String screeningRoom;
		try {
			screeningRoom = screeningRoomService.findById(screeningRoomId);
		} catch (RuntimeException e) {
			JSONObject obj = new JSONObject();
			String error = "There is no such screening room";
			obj.put("error", error);
			return new ResponseEntity<>(obj.toString(2), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(screeningRoom, HttpStatus.OK);
	}
}
