package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;

import java.util.Collection;

public interface PersistenceService {
    boolean save(User user);
    boolean requestFriendship(User userFrom, String userTo);
    boolean acceptFriendship(User userFrom, String userTo);
    boolean declineFriendship(User userFrom, String userTo);
    Collection<User> listFriends(User userFrom);
}
