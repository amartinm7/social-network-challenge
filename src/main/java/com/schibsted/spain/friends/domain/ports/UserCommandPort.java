package com.schibsted.spain.friends.domain.ports;

public interface UserCommandPort {
    boolean save(String username, String password);
    boolean requestFriendship(String userFrom, String userTo);
    boolean acceptFriendship(String userFrom, String userTo);
    boolean declineFriendship(String userFrom, String userTo);
    boolean remove(String username);
}
