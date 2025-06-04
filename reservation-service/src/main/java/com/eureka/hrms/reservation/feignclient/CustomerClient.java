package com.eureka.hrms.reservation.feignclient;

import com.eureka.hrms.reservation.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path="/api/v1/customers")
public interface CustomerClient {
    @GetMapping("/{customerId}")
    Customer getCustomer(@PathVariable("customerId") Long customerId);
}
