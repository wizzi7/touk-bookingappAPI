package com.touk.springboot.bookingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touk.springboot.bookingapp.dao.ClientRepository;
import com.touk.springboot.bookingapp.entity.Client;
import com.touk.springboot.bookingapp.entity.Reservation;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
		
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findById(int clientId) {
		Optional<Client> result = clientRepository.findById(clientId);
		Client Client = null;
		if (result.isPresent()) {
			Client = result.get();
		}
		else {
			throw new RuntimeException("Client id not found - " + clientId);
		}
		return Client;
	}
	
	@Override
	public Client save(Client client, Reservation reservation) {
		client.setReservation(reservation);
		return clientRepository.save(client);
	}
}
