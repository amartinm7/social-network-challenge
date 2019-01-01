package com.schibsted.spain.friends.application;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FriendShipServiceImpl implements FriendShipService{

    private final UserQueryPort userQueryPort;
    private final UserCommandPort userCommandPort;

    public FriendShipServiceImpl(@Autowired UserQueryPort userQueryPort, @Autowired UserCommandPort userCommandPort){
        this.userQueryPort = userQueryPort;
        this.userCommandPort = userCommandPort;
    }

    @Override
    public Collection<User> listFriends(String userFrom) {
        return userQueryPort.listFriends(userFrom);
    }


    @Override
    public Optional<User> requestFriendship(String userFrom, String userTo) {
        return userCommandPort.requestFriendship(userFrom, userTo);
    }

    @Override
    public Optional<User> acceptFriendship(String userFrom, String userTo) {
        return userCommandPort.acceptFriendship(userFrom, userTo);
    }

    @Override
    public Optional<User> declineFriendship(String userFrom, String userTo) {
        return userCommandPort.declineFriendship(userFrom, userTo);
    }
}