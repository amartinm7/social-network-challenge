package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CustomResponse {

    default ResponseEntity<ResponseMessage> getOKMessage (User user, ResponseMessage.Action action){
        final ResponseMessage<User> responseMessage = new ResponseMessage<>(user, HttpStatus.OK.value(), action);
        return new ResponseEntity(responseMessage, HttpStatus.OK );
    }

    default ResponseEntity<ResponseMessage> getBadRequestMessage (ResponseMessage.Action action){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), action);
        return new ResponseEntity( responseMessage, HttpStatus.BAD_REQUEST);
    }
}
