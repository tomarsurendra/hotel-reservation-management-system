package com.eureka.hrms.reservation.feignclient;

import com.eureka.hrms.reservation.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", path="/api/v1/payments")
public interface PaymentClient {
    @PostMapping
    Payment processPayment(Payment payment);

    @PostMapping("{paymentId}/refund")
    Payment refundPayment(@PathVariable("paymentId") Long paymentId);
}
