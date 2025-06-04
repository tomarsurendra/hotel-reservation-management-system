package com.eureka.hrms.hotelmanagement.repository;

import com.eureka.hrms.hotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByHotelIdAndRoomNumber(Long id, Long roomNumber);
}
