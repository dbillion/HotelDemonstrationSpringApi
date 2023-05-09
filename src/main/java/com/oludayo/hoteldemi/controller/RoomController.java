package com.oludayo.hoteldemi.controller;

import java.util.List;
import java.util.Optional;

import com.oludayo.hoteldemi.entity.Room;
import com.oludayo.hoteldemi.enums.RoomType;
import com.oludayo.hoteldemi.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            updatedRoom.setId(id);
            return ResponseEntity.ok(roomService.saveRoom(updatedRoom));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{roomType}")
    public List<Room> findRoomsByRoomType(@PathVariable RoomType roomType) {
        return roomService.findRoomsByRoomType(roomType);
    }

    @GetMapping("/discount/{discount}")
    public List<Room> findRoomsByDiscount(@PathVariable Double discount) {
        return roomService.findRoomsByDiscount(discount);
    }

    @GetMapping("/price/{price}")
    public List<Room> findRoomsByPrice(@PathVariable Double price) {
        return roomService.findRoomsByPrice(price);
    }
}
