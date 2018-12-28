package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupAdapter implements SignupService {
    private static final Logger logger = LoggerFactory.getLogger(SignupAdapter.class);
    private final PersistenceService persistenceService;
    public SignupAdapter (@Autowired PersistenceService persistenceService){
        this.persistenceService = persistenceService;
    }

    @Override
    public boolean signup(User user) {
        logger.info("signup user...");
        return persistenceService.save(user);
    }
}
