package com.nasr.productservice.core.exception;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    public static final String ERROR_KEY="errors";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> errorMessages = e.getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Map.Entry<String,List<String>> entry = Map.entry(ERROR_KEY,errorMessages);
        return ResponseEntity.status(BAD_REQUEST)
                .body(entry);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception e){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({CommandExecutionException.class,IllegalStateException.class})
    public ResponseEntity<?> handleCommandExecutionException(Exception e){
        HttpStatus status= BAD_REQUEST;

        return e instanceof IllegalStateException ?
                ResponseEntity.status(status).body(e.getMessage()) :
                ResponseEntity.status(status).body(e.getCause().getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(NOT_FOUND)
                .body(e.getMessage());
    }

}
