package by.itacademy.taskservice.core.exception.global.errors;


import by.itacademy.taskservice.core.exception.global.enums.ErrorType;

import java.util.Map;

public class StructuredErrorResponse {
    private ErrorType logref;
    private Map<String, String> errors;

    public StructuredErrorResponse() {
    }

    public StructuredErrorResponse(ErrorType logref, Map<String, String> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public ErrorType getLogref() {
        return logref;
    }

    public void setLogref(ErrorType logref) {
        this.logref = logref;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
