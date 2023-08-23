package com.example.reportservice.core.exception;

public class VerificationException extends RuntimeException{
    public VerificationException() {
    }

    public VerificationException(String massage) {
        super(massage);
    }
}
