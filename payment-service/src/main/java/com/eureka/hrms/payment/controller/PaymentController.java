package com.eureka.hrms.payment.controller;

import com.eureka.hrms.payment.entity.Payment;
import com.eureka.hrms.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.save(payment));
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<?> refundPayment(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(paymentService.refundPayment(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(paymentService.findById(id));
    }
}
