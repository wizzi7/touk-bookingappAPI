package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Movie;

public interface MovieService {

	public Movie findById(int movieId);

	public List<Movie> findAll();
	
}
