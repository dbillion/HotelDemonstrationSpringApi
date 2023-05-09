package com.oludayo.hoteldemi.controller;


import java.util.List;
import java.util.Optional;

import com.oludayo.hoteldemi.entity.Booking;
import com.oludayo.hoteldemi.entity.Payment;
import com.oludayo.hoteldemi.services.BookingService;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Optional<Booking> booking = bookingService.findById(id);
        if (booking.isPresent()) {
            updatedBooking.setId(id);
            return ResponseEntity.ok(bookingService.saveBooking(updatedBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/byCheckInDate")
    public ResponseEntity<List<Booking>> findAllByOrderByCheckInDate(
            @RequestParam("order") String order) {
        List<Booking> bookings = order.equalsIgnoreCase("desc")
                ? bookingService.findAllByOrderByCheckInDateDesc()
                : bookingService.findAllByOrderByCheckInDateAsc();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/byCheckOutDate")
    public ResponseEntity<List<Booking>> findAllByOrderByCheckOutDate(
            @RequestParam("order") String order) {
        List<Booking> bookings = order.equalsIgnoreCase("desc")
                ? bookingService.findAllByOrderByCheckOutDateDesc()
                : bookingService.findAllByOrderByCheckOutDateAsc();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


    @GetMapping("/byFirstName")
    public ResponseEntity<List<Booking>> findByPersonFirstName(
            @RequestParam("firstName") String firstName,
            @RequestParam("order") String order) {
        Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "person.firstName");
        List<Booking> bookings = bookingService.findByPersonFirstName(firstName, sort);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/byLastName")
    public ResponseEntity<List<Booking>> findByPersonLastName(
            @RequestParam("lastName") String lastName,
            @RequestParam("order") String order) {
        Sort sort = Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, "person.lastName");
        List<Booking> bookings = bookingService.findByPersonLastName(lastName, sort);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment newPayment = bookingService.createPayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}/payment")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Long bookingId) {
        Payment payment = bookingService.getPaymentByBookingId(bookingId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}