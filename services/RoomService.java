package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Room;
import com.example.demo.repositories.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room create(Room room) {
        return roomRepository.save(room);
    }

    public Room update(Long id, Room roomDetails) {
        return roomRepository.findById(id).map(room -> {
            room.setNumber(roomDetails.getNumber());
            room.setType(roomDetails.getType());
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with id " + id));
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
