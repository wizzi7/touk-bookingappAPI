package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touk.springboot.bookingapp.dao.RowRepository;

import com.touk.springboot.bookingapp.entity.Row;

@Service
public class RowServiceImpl implements RowService {

	@Autowired
    private RowRepository rowRepository;
	
	@Override
	public List<Row> findAll() {
		return rowRepository.findAll();
	}

	@Override
	public Row findById(int rowId) {
		Optional<Row> result = rowRepository.findById(rowId);
		Row row = null;
		if (result.isPresent()) {
			row = result.get();
		}
		else {
			throw new RuntimeException("Row id not found - " + rowId);
		}
		return row;
	}
}
