package com.schibsted.spain.friends.service;
import com.schibsted.spain.friends.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FriendShipAdapter implements FriendShipService{
    private static final Logger logger = LoggerFactory.getLogger(FriendShipAdapter.class);

    private final PersistenceService persistenceService;

    public FriendShipAdapter(@Autowired PersistenceService persistenceService){
        this.persistenceService = persistenceService;
    }

    @Override
    public boolean requestFriendship(User userFrom, String userTo) {
        return persistenceService.requestFriendship(userFrom, userTo);
    }

    @Override
    public boolean acceptFriendship(User userFrom, String userTo) {
        return persistenceService.acceptFriendship(userFrom, userTo);
    }

    @Override
    public boolean declineFriendship(User userFrom, String userTo) {
        return persistenceService.declineFriendship(userFrom, userTo);
    }

    @Override
    public Collection<User> listFriends(User userFrom) {
        return persistenceService.listFriends(userFrom);
    }
}
