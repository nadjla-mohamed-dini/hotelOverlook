package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo1.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
