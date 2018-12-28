package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;
import java.util.Collection;

public interface FriendShipService {
    boolean requestFriendship(String userFrom, String userTo);
    boolean acceptFriendship(String userFrom, String userTo);
    boolean declineFriendship(String userFrom, String userTo);
    Collection<User> listFriends(String userFrom);
}
