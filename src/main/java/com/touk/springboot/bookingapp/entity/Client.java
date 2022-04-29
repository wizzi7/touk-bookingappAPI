package com.touk.springboot.bookingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="client_id")
	private int clientId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@OneToOne
	@JoinColumn(name="reservation_id")
	private Reservation reservation;

	public Client() {
	}

	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", surname=" + surname + ", reservation="
				+ reservation + "]";
	}
}
