package com.eureka.hrms.reservation.model;

import lombok.Data;

@Data
public class Customer {

    private Long id;

    private String name;

    private String email;

    private String password;

}