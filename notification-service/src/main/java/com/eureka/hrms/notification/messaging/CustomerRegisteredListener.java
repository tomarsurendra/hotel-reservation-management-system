package com.eureka.hrms.notification.messaging;

import com.eureka.hrms.notification.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerRegisteredListener {
    @Autowired
    JavaMailSender javaMailSender;

    @KafkaListener(topics = "NewCustomerSignedUp", groupId = "myGroup")
    public void sendEmail(Customer customer) {
        log.info("Received Message in group myGroup: " + customer);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@amhotels.com");
        message.setTo(customer.getEmail());
        message.setSubject("Welcome Email");
        message.setText("<h1>Welcome to AM Hotel Reservation System!! Book today.</h1>");
        javaMailSender.send(message);
    }
}
