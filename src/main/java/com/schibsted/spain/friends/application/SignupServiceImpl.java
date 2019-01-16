package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO: TO KEEP IN MIND AS IDEA
//YOU CAN USE THE APPLICATION LAYER TO IMPLEMENT THE CIRCUIT BREAKER PATTERN IF IT WAS NEEDED
//IN FACT HYXTRIS USE THIS LAYER IN THE SERVICES TO DO IT. HYXTRIS USE THE CLASSES WITH SERVICE ANNOTATION FOR CONVENTION.
@Service
public class SignupServiceImpl implements SignupService {

    private final UserCommandPort userCommandPort;

    public SignupServiceImpl(@Autowired UserCommandPort userCommandPort){
        this.userCommandPort = userCommandPort;
    }

    @Override
    public Optional<User> signup(String username, String password) {
        return userCommandPort.save(username, password);
    }
}
