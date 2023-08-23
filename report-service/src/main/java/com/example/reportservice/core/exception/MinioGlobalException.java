package com.example.reportservice.core.exception;

public class MinioGlobalException extends RuntimeException{
    public MinioGlobalException() {
    }

    public MinioGlobalException(String message) {
        super(message);
    }
}
