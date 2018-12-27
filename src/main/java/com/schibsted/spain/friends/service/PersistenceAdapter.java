package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.persistence.CustomStorageProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceAdapter implements PersistenceService{
    private static final Logger logger = LoggerFactory.getLogger(PersistenceAdapter.class);

    private final CustomStorageProviderService customStorageProviderService;
    public PersistenceAdapter(@Autowired CustomStorageProviderService customStorageProviderService){
        this.customStorageProviderService = customStorageProviderService;
    }

    @Override
    public void save(User user){
        this.customStorageProviderService.save(user);
    }
}
