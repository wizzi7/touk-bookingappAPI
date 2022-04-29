package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Row;

public interface RowService {
	
	public Row findById(int rowId);

	public List<Row> findAll();

}
