package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.infrastructure.messages.CustomResponse;
import com.schibsted.spain.friends.infrastructure.messages.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler
    extends ResponseEntityExceptionHandler {

        //TODO: TO KEEP IN MIND AS IDEA
        //the microservice is designed to throw IllegalArgumentException for the validations
        //this is the global point to handle them in this challenge.
        //Please think that this is one approach for this challenge,
        //It doesn't mean that this has to be done in the same way in another projects
        @ExceptionHandler({ IllegalArgumentException.class })
        public ResponseEntity<ResponseMessage> handleIllegalArgumentException(
                Exception ex, WebRequest request) {
            return CustomResponse.getBadRequestMessage(
                    String.format("%s", ex.getMessage()));
        }
}
