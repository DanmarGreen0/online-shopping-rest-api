package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {DuplicateUsernameException.class})
    public ResponseEntity<?> handleControllerRequestException(DuplicateUsernameException ex){

        HttpStatus conflict = HttpStatus.CONFLICT;
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex,
                                            conflict, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(errorDetails, conflict);
    }

}
