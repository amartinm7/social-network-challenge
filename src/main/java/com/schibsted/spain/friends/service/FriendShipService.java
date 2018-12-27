package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;

import java.util.List;

public interface FriendShipService {
    boolean requestFriendship(User user, String userTo);
    boolean acceptFriendship(User user, String userTo);
    boolean declineFriendship(User user, String userTo);
    List<User> listFriends(User user);
}
