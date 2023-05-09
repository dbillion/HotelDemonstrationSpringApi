package com.oludayo.hoteldemi.services;

import  java.util.List;
import java.util.Optional;

import com.oludayo.hoteldemi.entity.Booking;
import com.oludayo.hoteldemi.entity.Payment;
import com.oludayo.hoteldemi.repository.BookingRepository;
import com.oludayo.hoteldemi.repository.PaymentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findAll() {
        return (List<Booking>) bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }


    public List<Booking> findByPersonFirstNameAndLastName(String firstName, String lastName) {
        return bookingRepository.findByPersonFirstNameAndLastName(firstName, lastName);
    }


    public List<Booking> findByPersonFirstName(String firstName, Sort sort) {
        return bookingRepository.findByPersonFirstName(firstName, sort);
    }

    public List<Booking> findByPersonLastName(String lastName, Sort sort) {
        return bookingRepository.findByPersonLastName(lastName, sort);
    }

    public List<Booking> findAllByOrderByCheckInDateAsc() {
        return bookingRepository.findAllByOrderByCheckInDateAsc();
    }

    public List<Booking> findAllByOrderByCheckInDateDesc() {
        return bookingRepository.findAllByOrderByCheckInDateDesc();
    }

    public List<Booking> findAllByOrderByCheckOutDateAsc() {
        return bookingRepository.findAllByOrderByCheckOutDateAsc();
    }

    public List<Booking> findAllByOrderByCheckOutDateDesc() {
        return bookingRepository.findAllByOrderByCheckOutDateDesc();
    }

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    public Payment getPaymentByBookingId(Long bookingId) {
        // Method implementation
        return paymentRepository.getById(bookingId.longValue());

    }

}
