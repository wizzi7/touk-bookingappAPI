package com.touk.springboot.bookingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ReservationViews;
import com.touk.springboot.bookingapp.jsonview.ScreeningRoomViews;

@Entity
@Table(name="seat")
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seat_id")
	private int seatId;
	
	@JsonView({ScreeningRoomViews.Normal.class, ReservationViews.Normal.class})
	@Column(name="number")
	private int number;
	
	@JsonView(ScreeningRoomViews.Normal.class)
	@Column(name="is_occupied")
	private boolean isOccupied;
	
	@ManyToOne
	@JoinColumn(name="room_row_id")
	@JsonView(ReservationViews.Normal.class)
	private Row row;
	
	@ManyToOne()
	@JoinColumn(name="reservation_id")
	@JsonIgnore
	private Reservation reservation;
	
	@OneToOne
	@JoinColumn(name="ticket_id")
	@JsonView(ReservationViews.Normal.class)
	private Ticket ticket;

	public Seat() {
	}

	public Seat(int number, boolean isOccupied) {
		this.number = number;
		this.isOccupied = isOccupied;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", number=" + number + ", isOccupied=" + isOccupied + ", row=" + row
				+ ", reservation=" + reservation + ", ticket=" + ticket + "]";
	}
}
