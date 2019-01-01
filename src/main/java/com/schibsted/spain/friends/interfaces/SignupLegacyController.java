package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import com.schibsted.spain.friends.application.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping(HttpParams.URI_SIGNUP)
public class SignupLegacyController {

  private static final Logger logger = LoggerFactory.getLogger(SignupLegacyController.class);

  private final SignupService signupService;

  public SignupLegacyController(@Autowired SignupService signupService){
    this.signupService = signupService;
  }

  @PostMapping
  public ResponseEntity<ResponseMessage> signUp(
      @RequestParam(HttpParams.USER_NAME) String username,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
    logger.info("asking for signup user {}", username);
    return signupService.signup(username, password)
            .map( user -> CustomResponse.getOKMessage(user, ResponseMessage.Action.SIGNUP) )
            .orElse( CustomResponse.getBadRequestMessage(ResponseMessage.Action.SIGNUP) );
  }
}
