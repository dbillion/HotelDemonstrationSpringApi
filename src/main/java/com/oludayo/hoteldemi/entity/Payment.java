package com.oludayo.hoteldemi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oludayo.hoteldemi.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;


    @OneToOne(mappedBy = "payment")
    @JsonBackReference
    private Booking booking;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentMethod;

    // Getters and setters
}