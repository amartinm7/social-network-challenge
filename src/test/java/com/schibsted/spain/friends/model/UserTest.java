package com.schibsted.spain.friends.model;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void buildEmptyUserShouldThrowException(){
        // given
        final String username = "";
        final String password = "password";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildEmptyPassShouldThrowException(){
        // given
        final String username = "johnny";
        final String password = "";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildShortNameShouldThrowException(){
        // given
        final String username = "john";
        final String password = "password";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }



    @Test
    public void buildShortPassShouldThrowException(){
        // given
        final String username = "johnny";
        final String password = "password";
        // then
        User user = new User.Builder().setName(username).setPassword(password).build();
        assertNotNull("The created object user is null", user);
    }
}
