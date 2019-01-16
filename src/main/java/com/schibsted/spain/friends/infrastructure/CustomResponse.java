package com.schibsted.spain.friends.infrastructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {

    public static <T> ResponseEntity<ResponseMessage> getOKMessage (T message){
        final ResponseMessage<T> responseMessage = new ResponseMessage<>(message, HttpStatus.OK.value());
        return new ResponseEntity(responseMessage, HttpStatus.OK );
    }

    public static <T> ResponseEntity<ResponseMessage> getBadRequestMessage (T message){
        final ResponseMessage<T> responseMessage = new ResponseMessage<>(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity( responseMessage, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseMessage> getInternalErrorMessage (){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity( responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
