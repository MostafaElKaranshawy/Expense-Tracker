package com.project.project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, String message, HttpStatus status) {
        return  new ResponseEntity<>(message + " " + e.getMessage(), status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex, String message, HttpStatus status) {
        return new ResponseEntity<>(message + " " + ex.getMessage(), status);
    }
}
