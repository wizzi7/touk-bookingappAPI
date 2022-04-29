package com.touk.springboot.bookingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ScreeningViews;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private int movieId;
	
	@Column(name="title")
	@JsonView(ScreeningViews.Normal.class)
	private String title;

	public Movie() {
	}

	public Movie(int movieId, String title) {
		this.movieId = movieId;
		this.title = title;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", title=" + title + "]";
	}
}
