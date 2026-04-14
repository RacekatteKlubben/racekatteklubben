package com.example.racekatteklubben.service.validation;

public class ValidationExceptionMember extends RuntimeException {
    public ValidationExceptionMember(String message) {
        super(message);
    }
}
