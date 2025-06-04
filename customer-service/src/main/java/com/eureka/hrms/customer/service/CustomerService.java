package com.eureka.hrms.customer.service;

import com.eureka.hrms.customer.entity.Customer;
import com.eureka.hrms.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer savedCustomer;
        try {
            savedCustomer = customerRepository.save(customer);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Invalid Customer Data", e);
        }
        return savedCustomer;
    }
}
