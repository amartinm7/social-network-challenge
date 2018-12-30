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
public class FriendshipLegacyController implements CustomResponse {

  private static final Logger logger = LoggerFactory.getLogger(FriendshipLegacyController.class);

  private FriendShipService friendShipService;

  public FriendshipLegacyController(@Autowired FriendShipService friendShipService){
    this.friendShipService = friendShipService;
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_REQUEST)
  ResponseEntity<ResponseMessage> requestFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("asking for requestFriendship...");
      return friendShipService.requestFriendship(usernameFrom, usernameTo)
              .map( user -> getOKMessage(user) )
              .orElse( getBadRequestMessage() );
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_ACCEPT)
  ResponseEntity<ResponseMessage> acceptFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("asking for acceptFriendship...");
      return friendShipService.acceptFriendship(usernameFrom, usernameTo)
              .map( user -> getOKMessage(user) )
              .orElse( getBadRequestMessage() );
  }

  @PostMapping(HttpParams.URI_FRIENDSHIP_DECLINE)
  ResponseEntity<ResponseMessage> declineFriendship(
      @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
      @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("asking for declineFriendship...");
      return friendShipService.declineFriendship(usernameFrom, usernameTo)
              .map( user -> getOKMessage(user) )
              .orElse( getBadRequestMessage() );
  }

  @GetMapping(HttpParams.URI_FRIENDSHIP_LIST)
  ResponseEntity<String[]> listFriends(
      @RequestParam(HttpParams.USER_NAME) String username,
      @RequestHeader(HttpParams.X_PASSWORD) String password) {
      logger.info("asking for listFriends...");
      final Collection<User> friends = friendShipService.listFriends(username);
      final String[] theFriends = friends.stream().map(friend -> friend.getName()).toArray(String[]::new);
      return new ResponseEntity<>(theFriends, HttpStatus.OK);
  }

}
