package com.nasr.userservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> illegalStateExceptionHandler(IllegalStateException e){
        return ResponseEntity.status(BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e){
        return ResponseEntity.status(NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}
