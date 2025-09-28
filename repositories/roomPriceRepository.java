package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.RoomPrice;

public interface roomPriceRepository extends JpaRepository<RoomPrice, Long>     {

    RoomPrice findByRoomTypeIgnoreCase(String type);
    
}
