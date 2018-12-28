package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import com.schibsted.spain.friends.application.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(HttpParams.URI_SIGNUP)
public class SignupLegacyController {

  private static final Logger logger = LoggerFactory.getLogger(SignupLegacyController.class);

  private final SignupService signupService;

  public SignupLegacyController(@Autowired SignupService signupService){
    this.signupService = signupService;
  }

  @PostMapping
  public ResponseEntity<String> signUp(
      @RequestParam(HttpParams.USER_NAME) String username,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
    logger.info("signup user...");
    if ( signupService.signup(username, password) ) {
      return new ResponseEntity<>(username, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(String.format("Invalid username/password: %s: %s ", username, password), HttpStatus.BAD_REQUEST);
    }
  }
}
