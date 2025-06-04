package com.eureka.hrms.reservation.service;

import com.eureka.hrms.reservation.entity.Reservation;
import com.eureka.hrms.reservation.exception.InvalidBusinessRuleException;
import com.eureka.hrms.reservation.feignclient.CustomerClient;
import com.eureka.hrms.reservation.feignclient.HotelClient;
import com.eureka.hrms.reservation.feignclient.PaymentClient;
import com.eureka.hrms.reservation.model.*;
import com.eureka.hrms.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private KafkaTemplate<String, ReservationNotification> kafkaTemplate;

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation save(Reservation customer) {
        return reservationRepository.save(customer);
    }

    public Reservation makeReservation(ReservationRequest request) {
        Customer customer = customerClient.getCustomer(request.getCustomerId());
        if (customer == null) {
            throw new InvalidBusinessRuleException("Customer does not exist. Please sign up");
        }
        RoomResponse roomResponse = hotelClient.checkRoomAvailablity(request.getHotelId(), request.getRoomNumber());
        if (roomResponse == null) {
            throw new InvalidBusinessRuleException("Room does not exist.");
        } else if ("BOOKED".equals(roomResponse.getRoomStatus())) {
            throw new InvalidBusinessRuleException("Room already booked, please choose another one.");
        }

        hotelClient.updateRoom(request.getHotelId(), request.getRoomNumber(), "BOOKED");
        request.getPayment().setCustomerId(request.getCustomerId());
        Payment payment;
        try{
            payment = processPayment(request);
        }catch (Exception e){
            //SAGA PATTERN- REVERT HOTEL ROOM BOOKING AS PAYMENT FAILED
            hotelClient.updateRoom(request.getHotelId(), request.getRoomNumber(), "AVAILABLE");
            throw new RuntimeException("Payment failed");
        }


        Reservation reservation = reservationRepository.save(mapReservationFromRequest(request, payment));

        ReservationNotification reservationNotification = createReservationNotification(request, customer);
        kafkaTemplate.send("ConfirmReservation", reservationNotification);
        return reservation;
    }

    private Payment processPayment(ReservationRequest request) {
        Payment payment = paymentClient.processPayment(request.getPayment());
        return payment;
    }

    private ReservationNotification createReservationNotification(ReservationRequest request, Customer customer) {
        Hotel hotel = hotelClient.getHotel(request.getHotelId());
        ReservationNotification reservationNotification = new ReservationNotification();
        reservationNotification.setStartDate(request.getCheckInDate());
        reservationNotification.setEndDate(request.getCheckOutDate());
        reservationNotification.setEmail(customer.getEmail());
        reservationNotification.setCustomerName(customer.getName());
        reservationNotification.setHotelName(hotel.getHotelName());
        return reservationNotification;
    }

    private static Reservation mapReservationFromRequest(ReservationRequest request, Payment payment) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(request.getCustomerId());
        reservation.setHotelId(request.getHotelId());
        reservation.setRoomNumber(request.getRoomNumber());
        reservation.setStartDate(LocalDate.parse(request.getCheckInDate()));
        reservation.setEndDate(LocalDate.parse(request.getCheckOutDate()));
        reservation.setPaymentId(payment.getId());
        reservation.setStatus("BOOKED");
        return reservation;
    }

    public String cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        paymentClient.refundPayment(reservation.getPaymentId());
        hotelClient.updateRoom(reservation.getHotelId(), reservation.getRoomNumber(), "AVAILABLE");
        Customer customer = customerClient.getCustomer(reservation.getCustomerId());
        reservation.setStatus("CANCELLED");
        reservationRepository.save(reservation);
        ReservationRequest reservationRequest = new ReservationRequest();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        reservationRequest.setHotelId(reservation.getHotelId());
        reservationRequest.setCheckInDate(reservation.getStartDate().format(formatter));
        reservationRequest.setCheckOutDate(reservation.getEndDate().format(formatter));
        kafkaTemplate.send("CancelReservation", createReservationNotification(reservationRequest, customer));
        return "Reservation Cancelled";
    }
}
