package com.oludayo.hoteldemi.repository;

import com.oludayo.hoteldemi.entity.Room;
import com.oludayo.hoteldemi.enums.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findRoomsByRoomTypeEquals(RoomType roomType);
    List<Room> findRoomsByDiscountEquals( Double discount);
    List<Room> findRoomsByPriceEquals( Double price);

}
