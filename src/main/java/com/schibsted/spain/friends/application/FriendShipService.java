package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;
import java.util.Collection;
import java.util.Optional;

public interface FriendShipService {
    Optional<User> requestFriendship(String userFrom, String userTo);
    Optional<User> acceptFriendship(String userFrom, String userTo);
    Optional<User> declineFriendship(String userFrom, String userTo);
    Collection<User> listFriends(String userFrom);
}
