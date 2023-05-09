package com.oludayo.hoteldemi.datasamplers;

import com.github.javafaker.Faker;
import com.oludayo.hoteldemi.entity.Room;
import com.oludayo.hoteldemi.enums.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoomGenerator {
    private static final int MAX_IMAGES = 4;

    public static List<Room> generateRooms(int count) {
        Faker faker = new Faker();
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    Room room = new Room();

                    List<String> images = new ArrayList<>();
                    for (int j = 0; j < MAX_IMAGES; j++) {
                        images.add(faker.internet().image());
                    }
                    room.setImages(images);

                    room.setPrice(faker.number().randomDouble(2, 50, 300));
                    room.setLocation(faker.address().fullAddress());
                    room.setDiscount(faker.number().randomDouble(2, 0, 50));
                    room.setRoomType(RoomType.values()[faker.number().numberBetween(0, RoomType.values().length)]);

                    return room;
                })
                .collect(Collectors.toList());
    }
}