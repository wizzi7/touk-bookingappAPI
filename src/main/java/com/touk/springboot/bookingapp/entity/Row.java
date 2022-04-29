package com.touk.springboot.bookingapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ScreeningRoomViews;

@Entity
@Table(name="room_row")
public class Row {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="room_row_id")
	private int rowId;
	
	@Column(name="seats")
	private int availableSeats;
	
	@JsonView(ScreeningRoomViews.Normal.class)
	@OneToMany(mappedBy="row")
	private List<Seat> seats;
	
	@ManyToOne
	@JoinColumn(name="screening_room_id")
	@JsonIgnore
	private ScreeningRoom screeningRoom;
	
	@JsonView(ScreeningRoomViews.Normal.class)
	@Column(name="name")
	private String name;

	public Row() {
	}

	public Row(String name, int availableSeats) {
		this.name = name;
		this.availableSeats = availableSeats;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int freeSeats) {
		this.availableSeats = freeSeats;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public ScreeningRoom getScreeningRoom() {
		return screeningRoom;
	}

	public void setScreeningRoom(ScreeningRoom screeningRoom) {
		this.screeningRoom = screeningRoom;
	}

	@Override
	public String toString() {
		return "Row [rowId=" + rowId + ", availableSeats=" + availableSeats + ", seats=" + seats + ", screeningRoom="
				+ screeningRoom + ", name=" + name + "]";
	}
}
