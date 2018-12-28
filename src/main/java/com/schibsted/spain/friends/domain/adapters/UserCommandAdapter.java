package com.schibsted.spain.friends.domain.adapters;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.infrastructure.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandAdapter
        implements UserCommandPort {
    private static final Logger logger = LoggerFactory.getLogger(UserCommandAdapter.class);

    private Repository<User,String> repository;

    public UserCommandAdapter(@Autowired Repository<User,String> repository){
        this.repository = repository;
    }

    @Override
    public boolean save(String username, String password){
        final User user = new User.Builder().setName(username).setPassword(password).build();
        return !isUserStored(username) && storeUser(user);
    }

    @Override
    public boolean requestFriendship(String userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = getUser(userFrom);
        final User savedUserTo = getUser(userTo);
        return savedUserFrom.requestFriendShip(savedUserTo);
    }

    @Override
    public boolean acceptFriendship(String userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = getUser(userFrom);
        final User savedUserTo = getUser(userTo);
        return savedUserFrom.acceptFriendShip(savedUserTo);
    }

    @Override
    public boolean declineFriendship(String userFrom, String userTo) {
        if ( !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = getUser(userFrom);
        final User savedUserTo = getUser(userTo);
        return savedUserFrom.declineFriendShip(savedUserTo);
    }

    private boolean storeUser(User user){ return repository.save(user); }
    private User getUser(String user){ return repository.find(user); }
    private boolean isUserStored(String user){ return repository.exists(user); }
}
