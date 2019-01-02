package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.interfaces.ResponseMessage;
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
        public ResponseEntity<ResponseMessage> handleIllegalArgumentException(
                Exception ex, WebRequest request) {
            return CustomResponse.getBadRequestMessage(
                    String.format("%d, %s", HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
}
