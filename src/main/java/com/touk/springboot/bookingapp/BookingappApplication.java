package com.touk.springboot.bookingapp;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingappApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(BookingappApplication.class, args);
	}
}
