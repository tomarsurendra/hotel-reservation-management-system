package com.eureka.hrms.reservation.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class InvalidBusinessRuleException extends RuntimeException {
    private String message;

    public InvalidBusinessRuleException(String message, RuntimeException e) {
        super(message, e);
        this.message = message;
    }

    public InvalidBusinessRuleException(String message) {
        this.message = message;
    }
}
