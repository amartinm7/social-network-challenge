package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.CustomResponse;
import com.schibsted.spain.friends.infrastructure.HttpParams;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.application.FriendShipService;
import com.schibsted.spain.friends.infrastructure.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public FriendshipLegacyController(@Autowired FriendShipService friendShipService) {
        this.friendShipService = friendShipService;
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_REQUEST,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ResponseMessage> requestFriendship(
            @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
            @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("asking for requestFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.requestFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_ACCEPT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> acceptFriendship(
            @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
            @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("asking for acceptFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.acceptFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @PostMapping(value = HttpParams.URI_FRIENDSHIP_DECLINE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> declineFriendship(
            @RequestParam(HttpParams.USER_NAME_FROM) String usernameFrom,
            @RequestParam(HttpParams.USER_NAME_TO) String usernameTo,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("asking for declineFriendship from {} to {}", usernameFrom, usernameTo);
        return friendShipService.declineFriendship(usernameFrom, usernameTo)
                .map(user -> CustomResponse.getOKMessage(user))
                .orElse(CustomResponse.getInternalErrorMessage());
    }

    @GetMapping(value = HttpParams.URI_FRIENDSHIP_LIST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> listFriends(
            @RequestParam(HttpParams.USER_NAME) String username,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("asking for listFriends for {}", username);
        final Collection<User> friends = friendShipService.listFriends(username);
        final String[] theFriends = friends.stream().map(friend -> friend.getName()).toArray(String[]::new);
        return new ResponseEntity<>(theFriends, HttpStatus.OK);
    }

    @GetMapping(value = HttpParams.URI_FRIENDSHIP_LIST_PENDING,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String[]> listPendingFriends(
            @RequestParam(HttpParams.USER_NAME) String username,
            @RequestHeader(HttpParams.X_PASSWORD) String password) {
        logger.info("asking for listPendingFriends for {}", username);
        final Collection<User> friends = friendShipService.listPendingFriends(username);
        final String[] theFriends = friends.stream().map(friend -> friend.getName()).toArray(String[]::new);
        return new ResponseEntity<>(theFriends, HttpStatus.OK);
    }

}
