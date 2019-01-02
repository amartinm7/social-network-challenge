package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.application.FriendShipService;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.infrastructure.CustomResponse;
import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * NEW IMPROVED VERSION OF THE LEGACY CONTROLLER.
 * THE URI PATHS ARE CHANGING FOR A MORE SEMANTIC VERSION
 * IN THIS VERSION THE USERNAMES ARE GETTING FROM THE URI AS THE REST SPECS SAYS INSTEAD OF GETTING AS QUERY PARAMETERS
 */
@RestController
@RequestMapping(HttpParams.URI_FRIENDSHIP)
public class FriendshipController {

    private static final Logger logger = LoggerFactory.getLogger(FriendshipLegacyController.class);

    private FriendShipService friendShipService;

    public FriendshipController(@Autowired FriendShipService friendShipService) {
        this.friendShipService = friendShipService;
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_REQUEST_V1,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> requestFriendship(
            @PathVariable(HttpParams.USER_NAME_FROM) String usernameFrom,
            @PathVariable(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("(improved api version) asking for requestFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.requestFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_ACCEPT_V1,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> acceptFriendship(
            @PathVariable(HttpParams.USER_NAME_FROM) String usernameFrom,
            @PathVariable(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("(improved api version) asking for acceptFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.acceptFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_DECLINE_V1,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> declineFriendship(
            @PathVariable(HttpParams.USER_NAME_FROM) String usernameFrom,
            @PathVariable(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("(improved api version) asking for declineFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.declineFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @GetMapping(value = HttpParams.URI_FRIENDSHIP_LIST_V1,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> listFriends(
            @PathVariable(HttpParams.USER_NAME) String username,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("(improved api version) asking for listFriends for {}", username);
        final Collection<User> friends = friendShipService.listFriends(username);
        final String[] theFriends = friends.stream().map(friend -> friend.getName()).toArray(String[]::new);
        return new ResponseEntity<>(theFriends, HttpStatus.OK);
    }

}
