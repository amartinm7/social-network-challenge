package com.schibsted.spain.friends.domain.ports;

import com.schibsted.spain.friends.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface UserQueryPort {
    Collection<User> listFriends(String userFrom);
    Collection<User> listPendingFriends(String userFrom);
    Optional<User> getUser(String user);

    boolean isAuthorizatedUser(String username, String password);
}
