package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomStorageProviderAdapter implements CustomStorageProviderService {
    private static final Logger logger = LoggerFactory.getLogger(CustomStorageProviderAdapter.class);

    private final Map<String,User> store = new HashMap<>();

    public boolean isAuthorizatedUser(String username, String password){
        if ( !isUserStored(username) ) {
           return false;
        }
        final User storedUser = store.get(username);
        return storedUser.getPassword().equals(password);
    }


    private boolean isUserStored(String user){
        return store.containsKey(user);
    }

    @Override
    public boolean save(User user){
        return !isUserStored(user.getName()) && (store.put(user.getName(),user) == null);
    }

    @Override
    public boolean requestFriendship(User userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.requestFriendShip(savedUserTo);
    }

    @Override
    public boolean acceptFriendship(User userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.acceptFriendShip(savedUserTo);
    }

    @Override
    public boolean declineFriendship(User userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = store.get(userFrom.getName());
        final User savedUserTo = store.get(userTo);
        return savedUserFrom.declineFriendShip(savedUserTo);
    }

    @Override
    public Collection<User> listFriends(User userFrom) {
        final User savedUserFrom = store.get(userFrom.getName());
        return savedUserFrom.getFriendList();
    }

}
