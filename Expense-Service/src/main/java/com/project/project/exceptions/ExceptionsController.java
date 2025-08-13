package com.project.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpStatus status) {
        return  new ResponseEntity<>(e.getMessage(), status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex, HttpStatus status) {
        return new ResponseEntity<>(ex.getMessage(), status);
    }
}
