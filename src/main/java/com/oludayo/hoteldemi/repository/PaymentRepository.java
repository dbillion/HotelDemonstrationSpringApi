package com.oludayo.hoteldemi.repository;

import com.oludayo.hoteldemi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {
}
