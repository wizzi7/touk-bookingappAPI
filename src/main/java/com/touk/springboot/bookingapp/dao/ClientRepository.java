package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
