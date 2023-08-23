package by.itacademy.taskservice.core.exception;

public class VerificationException extends RuntimeException{
    public VerificationException() {
    }

    public VerificationException(String massage) {
        super(massage);
    }
}
