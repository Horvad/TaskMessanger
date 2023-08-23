package by.itacademy.taskservice.core.exception.global.errors;


import by.itacademy.taskservice.core.exception.global.enums.ErrorType;

public class ErrorResponse {
    private ErrorType logref;
    private String massage;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorType logref, String massage) {
        this.logref = logref;
        this.massage = massage;
    }

    public ErrorType getLogref() {
        return logref;
    }

    public void setLogref(ErrorType logref) {
        this.logref = logref;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
