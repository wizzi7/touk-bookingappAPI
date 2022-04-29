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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ReservationViews;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private int reservationId;
	
	@OneToMany(mappedBy="reservation")
	@JsonView(ReservationViews.Normal.class)
	private List<Seat> seats;
	
	@ManyToOne
	@JoinColumn(name="screening_id")
	@JsonView(ReservationViews.Normal.class)
	private Screening screening;
	
	@OneToOne(mappedBy="reservation")
	private Client client;
	
	public Reservation() {
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", seats=" + seats + ", screening=" + screening
				+ ", client=" + client + "]";
	}
}
