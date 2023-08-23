package by.itAcademy.userservice.core.exception;

public class MailException extends RuntimeException{
    public MailException() {
    }

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
