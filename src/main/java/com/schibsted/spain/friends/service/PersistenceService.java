package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;

public interface PersistenceService {
    void save(User user);
}
