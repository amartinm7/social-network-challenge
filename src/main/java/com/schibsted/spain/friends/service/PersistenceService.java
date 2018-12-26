package com.schibsted.spain.friends.service;

import com.schibsted.spain.friends.model.User;

public interface PersistenceService {
    boolean save(User user);
    boolean existsUser(User user);
}
