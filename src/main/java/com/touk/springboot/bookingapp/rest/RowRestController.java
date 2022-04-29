package com.touk.springboot.bookingapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touk.springboot.bookingapp.entity.Row;
import com.touk.springboot.bookingapp.service.RowService;

@RestController
@RequestMapping("/api")
public class RowRestController {
	
	@Autowired
	private RowService rowService;
	
	@GetMapping("/rows")
	public List<Row> findAll(){
		return rowService.findAll();
	}
	
	@GetMapping("/rows/{rowId}")
	public Row getRow(@PathVariable int rowId){
		Row row = rowService.findById(rowId);
		if (row == null) {
			throw new RuntimeException("Row id not found - " + rowId);
		}
		return row;
	}
}
