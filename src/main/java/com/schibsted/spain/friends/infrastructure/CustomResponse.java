package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.interfaces.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {

    public static ResponseEntity<ResponseMessage> getOKMessage (User user){
        final ResponseMessage<User> responseMessage = new ResponseMessage<>(user, HttpStatus.OK.value());
        return new ResponseEntity(responseMessage, HttpStatus.OK );
    }

    public static ResponseEntity<ResponseMessage> getBadRequestMessage (String message){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity( responseMessage, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseMessage> getInternalErrorMessage (){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity( responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
