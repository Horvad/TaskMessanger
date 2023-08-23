package by.itacademy.taskservice.core.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String massage) {
        super(massage);
    }
}
