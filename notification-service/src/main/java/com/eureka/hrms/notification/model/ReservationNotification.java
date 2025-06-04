package com.eureka.hrms.notification.model;

import lombok.Data;

@Data
public class ReservationNotification {
    private String customerName;
    private String email;
    private String startDate;
    private String endDate;
    private String hotelName;
}
