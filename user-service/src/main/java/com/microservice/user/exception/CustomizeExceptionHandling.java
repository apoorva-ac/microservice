package com.microservice.user.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class CustomizeExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserException(UserNotFoundException ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true)), HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(new ExceptionResponse(new Date(), "Validation Failed.", ex.getBindingResult().toString()), HttpStatus.BAD_REQUEST);
    }

}
