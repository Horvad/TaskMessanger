package by.itacademy.taskservice.core.exception;

public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException() {
    }

    public IncorrectDataException(String massage) {
        super(massage);
    }
}
