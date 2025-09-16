package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Room;
public interface  RoomRepository extends JpaRepository<Room, Long>{}
