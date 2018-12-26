package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;

public interface CustomStorageProviderService {
    boolean save(User user);
    boolean existsUser(User user);
}
