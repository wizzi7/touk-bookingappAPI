package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
	

}
