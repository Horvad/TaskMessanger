package com.example.reportservice.inPoint.untils;

import com.example.reportservice.core.exception.*;
import com.example.reportservice.core.exception.global.enums.ErrorType;
import com.example.reportservice.core.exception.global.errors.ErrorResponse;
import com.example.reportservice.core.exception.global.errors.StructuredErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleErrorFeign(FeignException e){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MinioGlobalException.class)
    public ResponseEntity<?> handleErrorMinio(MinioGlobalException e){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileGlobalException.class)
    public ResponseEntity<?> handleErrorFile(FileGlobalException e){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();
        errors.put(exception.getConstraintName(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundError(NotFoundException exception){
        if(exception.getField() != null){
            StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
            response.getErrors().put(exception.getField(), exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VersionException.class)
    public ResponseEntity<ErrorResponse> handleVersionsMathError(VersionException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginError(LoginException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ErrorResponse> handleUserNotActivatedError(VersionException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDataError(IncorrectDataException exception){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<?> handleNPE(){
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(ErrorType.ERROR, "No data"));
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(errors);
    }
}
