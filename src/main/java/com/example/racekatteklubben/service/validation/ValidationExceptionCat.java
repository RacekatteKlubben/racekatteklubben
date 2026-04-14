package com.example.racekatteklubben.service.validation;

public class ValidationExceptionCat extends RuntimeException {
    public ValidationExceptionCat(String message) {
        super(message);
    }
}
