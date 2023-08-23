package by.itacademy.taskservice.core.exception;

public class FeignException extends RuntimeException{
    public FeignException() {
    }

    public FeignException(String message) {
        super(message);
    }
}
