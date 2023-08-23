package by.itAcademy.userservice.core.exception;

public class TokenException extends RuntimeException{
    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
