package com.oludayo.hoteldemi.datasamplers;

import com.github.javafaker.Faker;
import com.oludayo.hoteldemi.entity.Booking;
import com.oludayo.hoteldemi.entity.Payment;
import com.oludayo.hoteldemi.entity.Person;
import com.oludayo.hoteldemi.enums.PaymentType;
import com.oludayo.hoteldemi.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingGenerator {
    private static final int MAX_IMAGES = 100;

    public static Booking generateBooking() {
        Faker faker = new Faker();
        Booking booking = new Booking();

        List<String> images = new ArrayList<>();
        for (int i = 0; i < MAX_IMAGES; i++) {
            images.add(faker.internet().image());
        }


        Date checkInDate = faker.date().past(30, TimeUnit.DAYS); // Set the maximum number of days back to 30 (or any desired value)
        LocalDate checkInDateLocal = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        booking.setCheckInDate(checkInDateLocal);

        Date checkOutDate = faker.date().future(7, TimeUnit.DAYS, checkInDate); // Set the maximum number of days forward to 7 (or any desired value) from the check-in date
        LocalDate checkOutDateLocal = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        booking.setCheckOutDate(checkOutDateLocal);

        booking.setImage(images.get(faker.number().numberBetween(0, MAX_IMAGES))); // Set a random image from the list
        booking.setRoomType(RoomType.values()[faker.number().numberBetween(0, RoomType.values().length)]);
        booking.setPerson(generateRandomPerson());
        booking.setPayment(generateRandomPayment());



        return booking;
    }

    public static List<Booking> generateBookings(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateBooking())
                .collect(Collectors.toList());
    }

    public static Person generateRandomPerson() {
        Faker faker = new Faker();
        Person person = new Person();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setPhoneNumber(faker.phoneNumber().phoneNumber());
        person.setOccupation(faker.job().title());
        return person;
    }

    public static Payment generateRandomPayment() {
        Faker faker = new Faker();
        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(faker.number().numberBetween(200, 1000000)));
        payment.setPaymentDate(LocalDateTime.ofInstant(faker.date().past(30, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()));
        payment.setPaymentMethod(PaymentType.values()[new Random().nextInt(PaymentType.values().length)]);

        return payment;
    }

}
