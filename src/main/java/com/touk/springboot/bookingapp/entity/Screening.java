package com.touk.springboot.bookingapp.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ScreeningViews;

@Entity
@Table(name="screening")
public class Screening {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="screening_id")
	private int screeningId;
	
	@Column(name="start")
	@JsonView(ScreeningViews.Normal.class)
	private LocalDateTime start;
	
	@ManyToOne
	@JoinColumn(name="movie_id")
	@JsonView(ScreeningViews.Normal.class)
	private Movie movie;
	
	@OneToOne
	@JoinColumn(name="screening_room_id")
	private ScreeningRoom screeningRoom;

	public Screening() {
	}

	public Screening(LocalDateTime start, Movie movie) {
		this.start = start;
		this.movie = movie;
	}

	public int getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(int screeningId) {
		this.screeningId = screeningId;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public ScreeningRoom getScreeningRoom() {
		return screeningRoom;
	}

	public void setScreeningRoom(ScreeningRoom screeningRoom) {
		this.screeningRoom = screeningRoom;
	}

	@Override
	public String toString() {
		return "Screening [screeningId=" + screeningId + ", start=" + start + ", movie=" + movie + ", screeningRoom="
				+ screeningRoom + "]";
	}
}
