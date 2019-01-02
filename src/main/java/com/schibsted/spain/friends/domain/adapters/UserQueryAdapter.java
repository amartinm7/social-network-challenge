package com.schibsted.spain.friends.domain.adapters;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import com.schibsted.spain.friends.infrastructure.RepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserQueryAdapter
        implements UserQueryPort {

    private RepositoryPort<User,String> repositoryPort;

    public UserQueryAdapter(@Autowired RepositoryPort<User,String> repositoryPort){
        this.repositoryPort = repositoryPort;
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

    @Override
    public Collection<User> listPendingFriends(String userFrom) {
        final User savedUserFrom = getUser(userFrom);
        return savedUserFrom.getPendingFriendsList();
    }

    private User getUser(String user){
        return repositoryPort.find(user);
    }
    private boolean isUserStored(String user){
        return repositoryPort.exists(user);
    }

}
