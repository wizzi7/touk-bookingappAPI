package com.touk.springboot.bookingapp.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.touk.springboot.bookingapp.entity.Screening;
import com.touk.springboot.bookingapp.service.ScreeningService;

@RestController
@RequestMapping("/api")
public class ScreeningRestController {
	
	@Autowired
	private ScreeningService screeningService;
	
	@GetMapping("/screenings")
	public List<Screening> findAll(){
		return screeningService.findAll();
	}

	@RequestMapping(value="/screenings/programme", method=RequestMethod.GET, produces="application/json")
	public String findAllByDateAndTime(@RequestHeader("date-time") String dateAndTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime date = LocalDateTime.parse(dateAndTime, formatter);
		return screeningService.findAllByDateAndTime(date);
	}
}
