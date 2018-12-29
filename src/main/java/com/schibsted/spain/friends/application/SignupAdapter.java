package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupAdapter implements SignupService {

    private final UserCommandPort userCommandPort;

    public SignupAdapter (@Autowired UserCommandPort userCommandPort){
        this.userCommandPort = userCommandPort;
    }

    @Override
    public boolean signup(String username, String password) {
        return userCommandPort.save(username, password);
    }
}
