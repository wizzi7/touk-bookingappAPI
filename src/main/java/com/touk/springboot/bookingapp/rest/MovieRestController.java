package com.touk.springboot.bookingapp.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.touk.springboot.bookingapp.entity.Movie;
import com.touk.springboot.bookingapp.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public List<Movie> findAll(){
		return movieService.findAll();
	}
	
	@GetMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable int movieId){
		Movie movie = movieService.findById(movieId);
		if (movie == null) {
			throw new RuntimeException("Movie id not found - " + movieId);
		}
		return movie;
	}
}
