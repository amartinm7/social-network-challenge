package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.application.FriendShipService;
import com.schibsted.spain.friends.infrastructure.messages.CustomResponse;
import com.schibsted.spain.friends.infrastructure.HttpParams;
import com.schibsted.spain.friends.infrastructure.messages.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HttpParams.URI_LOGIN)
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  private FriendShipService friendShipService;

  public LoginController(@Autowired FriendShipService friendShipService){
    this.friendShipService = friendShipService;
  }

  @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseMessage> login(
      @RequestParam(HttpParams.USER_NAME) String username,
      @RequestParam(HttpParams.PASSWORD) String password) {
    logger.info("asking for login user {}", username);
    return friendShipService.getUser(username)
            .map( user -> CustomResponse.getOKMessage(user) )
            .orElse(CustomResponse.getInternalErrorMessage());
  }
}
