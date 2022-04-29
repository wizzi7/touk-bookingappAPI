package com.touk.springboot.bookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.touk.springboot.bookingapp.entity.ScreeningRoom;

public interface ScreeningRoomRepository extends JpaRepository<ScreeningRoom, Integer> {

}
