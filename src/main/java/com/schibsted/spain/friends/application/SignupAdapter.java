package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupAdapter implements SignupService {
    private static final Logger logger = LoggerFactory.getLogger(SignupAdapter.class);

    private final UserCommandPort userCommandPort;

    public SignupAdapter (@Autowired UserCommandPort userCommandPort){
        this.userCommandPort = userCommandPort;
    }

    @Override
    public boolean signup(String username, String password) {
        logger.info("signup user...");
        return userCommandPort.save(username, password);
    }
}
