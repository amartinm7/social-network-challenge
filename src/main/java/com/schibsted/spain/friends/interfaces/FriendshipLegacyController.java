package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.application.FriendShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(HttpParams.URI_FRIENDSHIP)
public class FriendshipLegacyController {
  private static final Logger logger = LoggerFactory.getLogger(FriendshipLegacyController.class);

  private FriendShipService friendShipService;

  public FriendshipLegacyController(@Autowired FriendShipService friendShipService){
    this.friendShipService = friendShipService;
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_REQUEST)
  ResponseEntity<String> requestFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("requestFriendship");
      try {
          final User user = new User.Builder().setName(usernameFrom).setPassword(password).build();
          if (friendShipService.requestFriendship(user, usernameTo)){
              return new ResponseEntity<>(user.toString(), HttpStatus.OK);
          } else {
              return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
          }
      } catch (Exception e) {
          return new ResponseEntity<>(String.format("Error: %s, %s", HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
      }
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_ACCEPT)
  ResponseEntity<String> acceptFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("acceptFriendship");
      try {
          final User user = new User.Builder().setName(usernameFrom).setPassword(password).build();
          if (friendShipService.acceptFriendship(user, usernameTo)){
              return new ResponseEntity<>(user.toString(), HttpStatus.OK);
          } else {
              return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
          }
      } catch (Exception e) {
          return new ResponseEntity<>(String.format("Error: %s, %s", HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
      }
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_DECLINE)
  ResponseEntity<String> declineFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("declineFriendship");
      try {
          final User user = new User.Builder().setName(usernameFrom).setPassword(password).build();
          if (friendShipService.declineFriendship(user, usernameTo)){
              return new ResponseEntity<>(user.toString(), HttpStatus.OK);
          } else {
              return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
          }
      } catch (Exception e) {
          return new ResponseEntity<>(String.format("Error: %s, %s", HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
      }
  }

  @GetMapping(HttpParams.URI_FRIENDSHIP_LIST)
  Object listFriends(
      @RequestParam(HttpParams.USER_NAME) String username,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("listFriends");
      try {
          final User user = new User.Builder().setName(username).setPassword(password).build();
          final Collection<User> friends = friendShipService.listFriends(user);
          final String[] theFriends = friends.stream().map(friend -> friend.getName()).toArray(String[]::new);
          return new ResponseEntity<String[]>(theFriends, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(String.format("Error: %s, %s", HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
      }
  }
}
