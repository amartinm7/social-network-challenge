package com.schibsted.spain.friends.infrastructure;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler
    extends ResponseEntityExceptionHandler {

        @ExceptionHandler({ IllegalArgumentException.class })
        public ResponseEntity<Object> handleAccessDeniedException(
                Exception ex, WebRequest request) {
            return new ResponseEntity<Object>(
                    String.format("%d, %s", HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST);
        }
}