package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomStorageProviderAdapter implements CustomStorageProviderService {
    private static final Logger logger = LoggerFactory.getLogger(CustomStorageProviderAdapter.class);

    private static final Map<String,User> store = new HashMap<>();

    private void validateExists(String user){
        if (!store.containsKey(user)){
            throw new IllegalArgumentException(String.format("Error, the user doesn't exists in the system: %s ", user));
        }
    }

    private void validateNotExists(String user){
        if (store.containsKey(user)){
            throw new IllegalArgumentException(String.format("Error, the user already exists in the system: %s ", user));
        }
    }

    @Override
    public void save(User user){
        logger.info(String.format("persisting user %s ", user.toString()));
        validateNotExists(user.getName());
        store.put(user.getName(),user);
    }

    @Override
    public boolean requestFriendship(User userFrom, String userTo) {
        validateExists(userFrom.getName());
        validateExists(userTo);
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.requestFriendShip(savedUserTo);
    }

    @Override
    public boolean acceptFriendship(User userFrom, String userTo) {
        validateExists(userFrom.getName());
        validateExists(userTo);
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.acceptFriendShip(savedUserTo);
    }

    @Override
    public boolean declineFriendship(User userFrom, String userTo) {
        validateExists(userFrom.getName());
        validateExists(userTo);
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.declineFriendShip(savedUserTo);
    }

    @Override
    public Collection<User> listFriends(User userFrom) {
        validateExists(userFrom.getName());
        final User savedUserFrom = store.get(userFrom.getName());
        return savedUserFrom.getFriendList();
    }

}
