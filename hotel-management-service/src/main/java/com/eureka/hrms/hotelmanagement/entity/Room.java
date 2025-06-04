package com.eureka.hrms.hotelmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    private Long id;
    private Long roomNumber;
    private String roomStatus;
    private String roomType;

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    @JsonBackReference
    private Hotel hotel;
}
