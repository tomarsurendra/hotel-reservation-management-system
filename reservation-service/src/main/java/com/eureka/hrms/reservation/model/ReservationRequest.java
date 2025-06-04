package com.eureka.hrms.reservation.model;

import lombok.Data;

@Data
public class ReservationRequest {
    private Long id;
    private Long customerId;
    private Long hotelId;
    private Long roomNumber;
    private String checkInDate;
    private String checkOutDate;
    private String bookingStatus;
    private Payment payment;
    private String roomType;
}
