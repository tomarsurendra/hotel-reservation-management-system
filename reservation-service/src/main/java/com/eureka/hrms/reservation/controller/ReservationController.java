package com.eureka.hrms.reservation.controller;

import com.eureka.hrms.reservation.model.ReservationRequest;
import com.eureka.hrms.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequest request) {
        return ResponseEntity.ok().body(reservationService.makeReservation(request));
    }

    @DeleteMapping("{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(reservationService.cancelReservation(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(reservationService.findById(id));
    }
}