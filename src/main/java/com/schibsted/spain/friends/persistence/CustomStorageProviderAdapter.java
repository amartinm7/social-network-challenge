package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CustomStorageProviderAdapter implements CustomStorageProviderService {
    private static final Logger logger = LoggerFactory.getLogger(CustomStorageProviderAdapter.class);

    private static final HashSet<User> store = new HashSet<>();

    @Override
    public boolean save(User user){
        logger.info(String.format("persisting user %s ", user.toString()));
        if (store.contains(user)){
            throw new IllegalArgumentException(String.format("Error, the user already exists in the system: %s ", user.toString()));
        }
        return store.add(user);
    }

    @Override
    public boolean existsUser(User user){
        return store.contains(user);
    }
}
