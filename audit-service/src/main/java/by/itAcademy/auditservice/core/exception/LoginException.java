package by.itAcademy.auditservice.core.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
