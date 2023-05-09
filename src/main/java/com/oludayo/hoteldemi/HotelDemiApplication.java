package com.oludayo.hoteldemi;

import com.oludayo.hoteldemi.datasamplers.BookingGenerator;
import com.oludayo.hoteldemi.datasamplers.RoomGenerator;
import com.oludayo.hoteldemi.entity.Booking;
import com.oludayo.hoteldemi.entity.Room;
import com.oludayo.hoteldemi.repository.BookingRepository;
import com.oludayo.hoteldemi.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class HotelDemiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HotelDemiApplication.class, args);

        BookingRepository bookingRepository = context.getBean(BookingRepository.class);
        RoomRepository roomRepository = context.getBean(RoomRepository.class);

        List<Booking> bookings = BookingGenerator.generateBookings(100);
        bookingRepository.saveAll(bookings);

        List<Room> rooms = RoomGenerator.generateRooms(50);
        roomRepository.saveAll(rooms);
    }

}
