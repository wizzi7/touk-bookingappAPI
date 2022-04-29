package com.touk.springboot.bookingapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touk.springboot.bookingapp.dao.ScreeningRepository;
import com.touk.springboot.bookingapp.entity.Screening;

@Service
public class ScreeningServiceImpl implements ScreeningService {
	
	@Autowired
	private ScreeningRepository screeningRepository;
	
	@Override
	public List<Screening> findAll() {
		return screeningRepository.findAll();
	}

	@Override
	public Screening findById(int screeningId) {
		Optional<Screening> result = screeningRepository.findById(screeningId);
		Screening screening = null;
		if (result.isPresent()) {
			screening = result.get();
		}
		else {
			throw new RuntimeException("Screening id not found - " + screeningId);
		}
		return screening;
	}
	
	@Override
	public String findAllByDateAndTime(LocalDateTime start) {
		List<Screening> screenings = screeningRepository.findAll();		
		screenings =  screenings.stream()
				.filter(sc -> sc.getStart().isAfter(start))
				.sorted(Comparator.comparing(sc -> sc.getStart()))
				.collect(Collectors.toList());
		
		 Map<String, List<String>> result = new TreeMap<>();
		 for (Screening sc : screenings) {
		     String title = sc.getMovie().getTitle();
			 List<String> dates;
			 if (result.containsKey(title)) {
				 if (result.get(title) == null) {
					 dates = new ArrayList<>();
					 dates.add(sc.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")));
				 }
				 else {
					 dates = result.get(title);
					 dates.add(sc.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")));
				 }
			 }
			 else {
				 dates = new ArrayList<>();
				 dates.add(sc.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")));
				 result.put(title, dates);
			 }
		  }
		 
		  JSONArray jsonArr = new JSONArray();
		  JSONObject finalJson = new JSONObject();
		  for (Map.Entry<String, List<String>> pair : result.entrySet()) {
			  JSONObject json = new JSONObject();
			  json.put("schedule", pair.getValue());
			  json.put("title", pair.getKey());
			  jsonArr.put(json);
		   }
		   finalJson.put("screenings", jsonArr);
	       return finalJson.toString(2);
	}
}
