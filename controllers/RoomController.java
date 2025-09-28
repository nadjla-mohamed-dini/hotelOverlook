package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Room;
import com.example.demo.services.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // GET toutes les chambres
    @GetMapping
    public List<Room> getAll() {
        return roomService.getAll();
    }

    // GET une chambre par ID
    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id) {
        return roomService.getById(id);
    }

    // POST créer une chambre
    @PostMapping
    public Room create(@RequestBody Room room) {
        return roomService.create(room);
    }

    // PUT modifier une chambre
    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        return roomService.update(id, room);
    }

    // DELETE supprimer une chambre
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }
}
