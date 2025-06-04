package com.eureka.hrms.payment.repository;

import com.eureka.hrms.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
