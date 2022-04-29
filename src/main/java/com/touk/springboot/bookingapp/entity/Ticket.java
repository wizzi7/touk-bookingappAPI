package com.touk.springboot.bookingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.touk.springboot.bookingapp.jsonview.ReservationViews;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_id")
	@JsonView(ReservationViews.Normal.class)
	private int ticketId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private Double price;

	public Ticket() {
	}

	public Ticket( String type, Double price) {
		this.type = type;
		this.price = price;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", type=" + type + ", price=" + price + "]";
	}
}
