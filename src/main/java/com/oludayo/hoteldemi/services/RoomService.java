package com.oludayo.hoteldemi.services;

import java.util.List;
import java.util.Optional;

import com.oludayo.hoteldemi.entity.Room;
import com.oludayo.hoteldemi.enums.RoomType;
import com.oludayo.hoteldemi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findRoomsByRoomType(RoomType roomType) {
        return roomRepository.findRoomsByRoomTypeEquals(roomType);
    }

    public List<Room> findRoomsByDiscount(Double discount) {
        return roomRepository.findRoomsByDiscountEquals(discount);
    }

    public List<Room> findRoomsByPrice(Double price) {
        return roomRepository.findRoomsByPriceEquals(price);
    }
}
