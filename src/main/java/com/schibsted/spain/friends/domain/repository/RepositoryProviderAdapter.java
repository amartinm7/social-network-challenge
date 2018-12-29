package com.schibsted.spain.friends.domain.repository;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.infrastructure.RepositoryPort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RepositoryProviderAdapter implements RepositoryPort<User,String> {

    private final Map<String, User> store = new HashMap<>();

    @Override
    public boolean save(User user) {
        return (store.put(user.getName(),user) == null);
    }

    @Override
    public boolean remove(String username) {
        return (store.remove(username)==null);
    }

    @Override
    public User find(String username) {
        return store.get(username);
    }

    @Override
    public boolean exists(String username) {
        return store.containsKey(username);
    }
}
