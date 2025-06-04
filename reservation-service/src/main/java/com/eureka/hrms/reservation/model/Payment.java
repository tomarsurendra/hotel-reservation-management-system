package com.eureka.hrms.reservation.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Payment {
    private Long id;
    private String modeOfPayment;
    private String status;
    private Long customerId;
    private BigDecimal amount;

}
