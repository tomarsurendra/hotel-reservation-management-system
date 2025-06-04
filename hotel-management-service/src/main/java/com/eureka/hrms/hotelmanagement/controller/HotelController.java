package com.eureka.hrms.hotelmanagement.controller;

import com.eureka.hrms.hotelmanagement.entity.Hotel;
import com.eureka.hrms.hotelmanagement.entity.Room;
import com.eureka.hrms.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    @Autowired
    private HotelService service;

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelDetails(@PathVariable Long id) {
        Hotel hotel = service.getHotel(id);
        return ResponseEntity.ok().body(hotel);
    }
    @GetMapping("/{id}/rooms/{roomNumber}")
    public ResponseEntity<Room> getAvailableRooms(@PathVariable Long id, @PathVariable Long roomNumber) {
        Room room = service.getRoom(id, roomNumber);
        return ResponseEntity.ok().body(room);
    }
    @GetMapping("/{id}/rooms/available")
    public ResponseEntity<List<Room>> getAvailableRooms(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAvailableRooms(id));
    }

    @PostMapping("/{id}/rooms/{roomNumber}/{roomStatus}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @PathVariable Long roomNumber, @PathVariable String roomStatus) {
        return  ResponseEntity.ok(service.updateRoom(id, roomNumber, roomStatus));
    }
}
