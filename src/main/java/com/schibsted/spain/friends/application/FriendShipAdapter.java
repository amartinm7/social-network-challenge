package com.schibsted.spain.friends.application;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FriendShipAdapter implements FriendShipService{

    private final UserQueryPort userQueryPort;
    private final UserCommandPort userCommandPort;

    public FriendShipAdapter(@Autowired UserQueryPort userQueryPort, @Autowired UserCommandPort userCommandPort){
        this.userQueryPort = userQueryPort;
        this.userCommandPort = userCommandPort;
    }

    @Override
    public boolean requestFriendship(String userFrom, String userTo) {
        return userCommandPort.requestFriendship(userFrom, userTo);
    }

    @Override
    public boolean acceptFriendship(String userFrom, String userTo) {
        return userCommandPort.acceptFriendship(userFrom, userTo);
    }

    @Override
    public boolean declineFriendship(String userFrom, String userTo) {
        return userCommandPort.declineFriendship(userFrom, userTo);
    }

    @Override
    public Collection<User> listFriends(String userFrom) {
        return userQueryPort.listFriends(userFrom);
    }
}
