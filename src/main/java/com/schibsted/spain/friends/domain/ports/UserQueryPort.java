package com.schibsted.spain.friends.domain.ports;

import com.schibsted.spain.friends.domain.User;

import java.util.Collection;

public interface UserQueryPort {
    Collection<User> listFriends(String userFrom);
    boolean isAuthorizatedUser(String username, String password);
}
