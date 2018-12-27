package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;

import java.util.Collection;

public interface CustomStorageProviderService {
    boolean save(User user);
    boolean requestFriendship(User userFrom, String userTo);
    boolean acceptFriendship(User userFrom, String userTo);
    boolean declineFriendship(User userFrom, String userTo);
    Collection<User> listFriends(User userFrom);
}
