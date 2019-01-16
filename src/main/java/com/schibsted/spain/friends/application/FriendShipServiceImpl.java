package com.schibsted.spain.friends.application;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

//TODO: TO KEEP IN MIND AS IDEA
//YOU CAN USE THE APPLICATION LAYER TO IMPLEMENT THE CIRCUIT BREAKER PATTERN IF IT WAS NEEDED
//IN FACT HYXTRIS USE THIS LAYER IN THE SERVICES TO DO IT. HYXTRIS USE THE CLASSES WITH SERVICE ANNOTATION FOR CONVENTION.
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
    public Collection<User> listPendingFriends(String userFrom)  { return userQueryPort.listPendingFriends(userFrom); }

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

    @Override
    public Optional<User> getUser(String userFrom) {
        return userQueryPort.getUser(userFrom);
    }
}
