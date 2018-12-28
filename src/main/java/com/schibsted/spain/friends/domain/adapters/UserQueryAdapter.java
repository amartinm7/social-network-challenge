package com.schibsted.spain.friends.domain.adapters;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import com.schibsted.spain.friends.infrastructure.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserQueryAdapter
        implements UserQueryPort {
    private static final Logger logger = LoggerFactory.getLogger(UserQueryAdapter.class);

    private Repository<User,String> repository;

    public UserQueryAdapter(@Autowired Repository<User,String> repository){
        this.repository = repository;
    }

    public boolean isAuthorizatedUser(String username, String password){
        if ( !isUserStored(username) ) {
           return false;
        }
        final User storedUser = getUser(username);
        return storedUser.getPassword().equals(password);
    }

    @Override
    public Collection<User> listFriends(String userFrom) {
        final User savedUserFrom = getUser(userFrom);
        return savedUserFrom.getFriendList();
    }

    private User getUser(String user){
        return repository.find(user);
    }
    private boolean isUserStored(String user){
        return repository.exists(user);
    }

}
