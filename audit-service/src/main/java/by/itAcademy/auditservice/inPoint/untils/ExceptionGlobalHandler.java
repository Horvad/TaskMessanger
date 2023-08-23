package by.itAcademy.auditservice.inPoint.untils;

import by.itAcademy.auditservice.core.exception.*;
import by.itAcademy.auditservice.core.exception.global.enums.ErrorType;
import by.itAcademy.auditservice.core.exception.global.errors.ErrorResponse;
import by.itAcademy.auditservice.core.exception.global.errors.StructuredErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException exception){
        StructuredErrorResponse response = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, new HashMap<>());
        Map<String, String> errors = response.getErrors();
        errors.put(exception.getConstraintName(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(){
        ErrorResponse response = new ErrorResponse(ErrorType.ERROR, "Не корректный запрос");
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

//

//500
//    @ExceptionHandler  //(value = {IllegalAccessException.class})
//    public ResponseEntity<List<ErrorForSingleResponse>> handleAll(Throwable e) {
//        List<ErrorForSingleResponse> errors = new ArrayList<>();
//        errors.add(new ErrorForSingleResponse("error",
//                "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"));
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(errors);
//           }


}
