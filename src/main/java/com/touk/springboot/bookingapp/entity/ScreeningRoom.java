package com.touk.springboot.bookingapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ScreeningRoomViews;

@Entity
@Table(name="screening_room")
public class ScreeningRoom {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="screening_room_id")
	private int screeningRoomId;
	
	@JsonView(ScreeningRoomViews.Normal.class)
	@Column(name="name")
	private String name;
	
	@Column(name="capacity")
	private int capacity;
	
	@JsonView(ScreeningRoomViews.Normal.class)
	@OneToMany(mappedBy="screeningRoom")
	private List<Row> rows;

	public ScreeningRoom() {
	}

	public ScreeningRoom(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public int getScreeningRoomId() {
		return screeningRoomId;
	}

	public void setScreeningRoomId(int screeningRoomId) {
		this.screeningRoomId = screeningRoomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ScreeningRoom [screeningRoomId=" + screeningRoomId + ", name=" + name + ", capacity=" + capacity
				+ ", rows=" + rows + "]";
	}
}
