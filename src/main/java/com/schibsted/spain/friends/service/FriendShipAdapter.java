package com.schibsted.spain.friends.service;
import com.schibsted.spain.friends.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendShipAdapter implements FriendShipService{
    private static final Logger logger = LoggerFactory.getLogger(FriendShipAdapter.class);

    @Override
    public boolean requestFriendship(User user, String userTo) {
        return false;
    }

    @Override
    public boolean acceptFriendship(User user, String userTo) {
        return false;
    }

    @Override
    public boolean declineFriendship(User user, String userTo) {
        return false;
    }

    @Override
    public List<User> listFriends(User user) {
        return null;
    }
}
