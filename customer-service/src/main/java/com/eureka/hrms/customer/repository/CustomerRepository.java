package com.eureka.hrms.customer.repository;

import com.eureka.hrms.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
