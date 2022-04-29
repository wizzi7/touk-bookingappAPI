package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.touk.springboot.bookingapp.dao.MovieRepository;
import com.touk.springboot.bookingapp.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findById(int movieId) {
		Optional<Movie> result = movieRepository.findById(movieId);
		Movie movie = null;
		if (result.isPresent()) {
			movie = result.get();
		}
		else {
			throw new RuntimeException("Movie id not found - " + movieId);
		}
		return movie;
	}
}
