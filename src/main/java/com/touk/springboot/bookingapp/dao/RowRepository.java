package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.Row;

public interface RowRepository extends JpaRepository<Row, Integer>{

}
