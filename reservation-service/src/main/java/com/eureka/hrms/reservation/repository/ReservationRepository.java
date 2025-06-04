package com.eureka.hrms.reservation.repository;

import com.eureka.hrms.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
