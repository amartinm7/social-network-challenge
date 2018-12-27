package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;

import java.util.Collection;

public interface PersistenceService {
    boolean save(User user);
    boolean requestFriendship(User userFrom, String userTo);
    boolean acceptFriendship(User userFrom, String userTo);
    boolean declineFriendship(User userFrom, String userTo);
    Collection<User> listFriends(User userFrom);
}
