package com.oludayo.hoteldemi.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oludayo.hoteldemi.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Builder
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomType roomType;
    private String image;
//    @ElementCollection
//    @CollectionTable(name = "booking_person", joinColumns = @JoinColumn(name = "booking_id"))
//    private Map<String, String> person = new HashMap<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    @JsonManagedReference
    private Payment payment;
}
