package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController {
  private static final Logger logger = LoggerFactory.getLogger(SignupLegacyController.class);
  private final SignupService signupService;
  public SignupLegacyController(@Autowired SignupService signupService){
    this.signupService = signupService;
  }

  @PostMapping
  public ResponseEntity<String> signUp(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password) {
    logger.info("signup user");
    try {
      final User user = new User.Builder().setName(username).setPassword(password).build();
      signupService.signup(user);
      logger.info("user stored... {} ", user);
      return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(String.format("Error: %s, %s", HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}
