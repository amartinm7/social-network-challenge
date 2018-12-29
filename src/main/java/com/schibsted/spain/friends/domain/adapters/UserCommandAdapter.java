package com.schibsted.spain.friends.domain.adapters;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.infrastructure.RepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class UserCommandAdapter
        implements UserCommandPort {

    private RepositoryPort<User,String> repositoryPort;

    public UserCommandAdapter(@Autowired RepositoryPort<User,String> repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public boolean save(String username, String password){
        final User user = new User.Builder().setName(username).setPassword(password).build();
        return !isUserStored(username) && storeUser(user);
    }

    @Override
    public boolean requestFriendship(String userFrom, String userTo) {
        return execute(userFrom, userTo,
                (savedUserFrom,savedUserTo) -> savedUserFrom.requestFriendShip(savedUserTo) );
    }

    @Override
    public boolean acceptFriendship(String userFrom, String userTo) {
        return execute(userFrom, userTo,
                (savedUserFrom,savedUserTo) -> savedUserFrom.acceptFriendShip(savedUserTo) );
    }

    @Override
    public boolean declineFriendship(String userFrom, String userTo) {
        return execute(userFrom, userTo,
                (savedUserFrom,savedUserTo) -> savedUserFrom.declineFriendShip(savedUserTo) );
    }

    public boolean execute(String userFrom, String userTo, BiFunction<User,User,Boolean> biFunction) {
        if ( !isUserStored(userFrom) || !isUserStored(userTo) ){
            return false;
        }
        final User savedUserFrom = getUser(userFrom);
        final User savedUserTo = getUser(userTo);
        return biFunction.apply(savedUserFrom, savedUserTo);
    }

    @Override
    public boolean remove(String userFrom) {
        return repositoryPort.remove(userFrom);
    }

    private boolean storeUser(User user){ return repositoryPort.save(user); }
    private User getUser(String user){ return repositoryPort.find(user); }
    private boolean isUserStored(String user){ return repositoryPort.exists(user); }

}
