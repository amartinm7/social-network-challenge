package com.schibsted.spain.friends.application;


import com.schibsted.spain.friends.domain.User;

import java.util.Optional;

public interface SignupService {
    Optional<User> signup (String username, String password);

}
