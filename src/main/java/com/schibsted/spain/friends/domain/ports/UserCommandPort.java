package com.schibsted.spain.friends.domain.ports;

import com.schibsted.spain.friends.domain.User;

import java.util.Optional;

public interface UserCommandPort {
    Optional<User> save(String username, String password);
    Optional<User> requestFriendship(String userFrom, String userTo);
    Optional<User> acceptFriendship(String userFrom, String userTo);
    Optional<User> declineFriendship(String userFrom, String userTo);
    boolean remove(String username);
}
