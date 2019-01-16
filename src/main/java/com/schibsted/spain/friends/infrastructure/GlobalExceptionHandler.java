package com.schibsted.spain.friends.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler
    extends ResponseEntityExceptionHandler {

        @ExceptionHandler({ IllegalArgumentException.class })
        public ResponseEntity<ResponseMessage> handleIllegalArgumentException(
                Exception ex, WebRequest request) {
            return CustomResponse.getBadRequestMessage(
                    String.format("%s", ex.getMessage()));
        }
}
