package com.touk.springboot.bookingapp.service;

import java.util.List;

import com.touk.springboot.bookingapp.entity.Client;
import com.touk.springboot.bookingapp.entity.Reservation;

public interface ClientService {

	public List<Client> findAll();

	public Client findById(int clientId);

	public Client save(Client client, Reservation reservation);

}
