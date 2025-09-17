package com.example.demo1.controller;
import com.example.demo1.models.Room;
import com.example.demo1.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        return roomService.save(room);
    }

    // PUT modifier une chambre
    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        return roomService.save(room);
    }

    // DELETE supprimer une chambre
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }
}
