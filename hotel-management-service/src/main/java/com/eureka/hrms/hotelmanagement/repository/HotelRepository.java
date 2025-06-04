package com.eureka.hrms.hotelmanagement.repository;

import com.eureka.hrms.hotelmanagement.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {}
