package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
