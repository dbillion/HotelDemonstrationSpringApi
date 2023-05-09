package com.oludayo.hoteldemi.entity;

import com.oludayo.hoteldemi.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private double price;
    private String location;
    private double discount;
    private RoomType roomType;

    @ElementCollection
    @Column(name = "image")
    private List<String> images;

    public Room() {
        images = new ArrayList<>(4);
    }

    public void addImage(String imageUrl) {
        if (images.size() < 4) {
            images.add(imageUrl);
        } else {
            throw new IllegalArgumentException("Room can only have up to 4 images.");
        }
    }
}