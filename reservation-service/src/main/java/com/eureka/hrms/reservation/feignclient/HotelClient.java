package com.eureka.hrms.reservation.feignclient;

import com.eureka.hrms.reservation.model.Hotel;
import com.eureka.hrms.reservation.model.RoomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hotel-service", path = "/api/v1/hotels")
public interface HotelClient {
    @GetMapping("/{hotelId}/rooms/{roomNumber}")
    RoomResponse checkRoomAvailablity(@PathVariable("hotelId") Long hotelId,@PathVariable("roomNumber") Long roomNumber);

    @PostMapping("/{hotelId}/rooms/{roomNumber}/{roomStatus}")
    RoomResponse updateRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomNumber") Long roomNumber,
                            @PathVariable("roomStatus") String roomStatus);

    @GetMapping("/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") Long hotelId);
}
