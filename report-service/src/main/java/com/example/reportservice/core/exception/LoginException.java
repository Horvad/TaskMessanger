package com.example.reportservice.core.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String massage) {
        super(massage);
    }
}
