package com.eureka.hrms.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Customer {

    private Long id;

    private String name;

    private String email;

    private String password;

}