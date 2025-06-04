package com.eureka.hrms.reservation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerId", nullable = false)
    private Long customerId;

    @Column(name = "paymentId", nullable = false)
    private Long paymentId;

    @Column(name = "hotelId", nullable = false)
    private Long hotelId;

    private Long roomNumber;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    private String status;

}