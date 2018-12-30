package com.schibsted.spain.friends.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    public void buildEmptyUserShouldThrowError(){
        // given
        final String username = "";
        final String password = "password";
        // then
        assertThrows (IllegalArgumentException.class,() -> new User.Builder().setName(username).setPassword(password).build(), "The user was created well but the params are wrong");
    }

    @Test
    public void buildEmptyPassShouldThrowError(){
        // given
        final String username = "johnny";
        final String password = "";
        // then
        assertThrows (IllegalArgumentException.class,() -> new User.Builder().setName(username).setPassword(password).build(), "The user was created well but the params are wrong");
    }

    @Test
    public void buildShortNameShouldThrowError(){
        // given
        final String username = "john";
        final String password = "password";
        // then
        assertThrows (IllegalArgumentException.class,() -> new User.Builder().setName(username).setPassword(password).build(), "The user was created well but the params are wrong");
    }

    @Test
    public void buildShortPasswordShouldThrowError(){
        // given
        final String username = "john";
        final String password = "pass";
        // then
        assertThrows (IllegalArgumentException.class,() -> new User.Builder().setName(username).setPassword(password).build(), "The user was created well but the params are wrong");
    }

    @Test
    public void buildUserShouldBeOK(){
        // given
        final String username = "johnny";
        final String password = "password";
        // then
        final User user = new User.Builder().setName(username).setPassword(password).build();
        assertNotNull("The created object user is null", user);
    }

    @Test
    public void requestFriendShipShouldBeTrue(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("Not valid request for friendship", userFrom.requestFriendShip(userTo).isPresent());
    }

    @Test
    public void twoRequestsFriendShipShouldBeFalse(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("Not valid request for friendship", userFrom.requestFriendShip(userTo).isPresent());
        assertFalse("Not valid request for friendship", userFrom.requestFriendShip(userTo).isPresent());
    }

    @Test
    public void acceptFriendShipShouldBeTrue(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("the request for friendship is ok but was error", userFrom.requestFriendShip(userTo).isPresent());
        assertFalse("the request for friendship is already done but was ok", userFrom.requestFriendShip(userTo).isPresent());
        assertTrue("Not accepted friendship", userTo.acceptFriendShip(userFrom).isPresent());
    }

    @Test
    public void acceptFriendShipShouldBeFalse(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo).isPresent());
        assertTrue("valid accepted friendship but was error", userTo.acceptFriendShip(userFrom).isPresent());
        assertFalse("Invalid accepted friendship but was ok", userTo.acceptFriendShip(userFrom).isPresent());
    }

    @Test
    public void declineFriendShipShouldBeOK(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo).isPresent());
        assertTrue("valid declined friendship but was error", userTo.declineFriendShip(userFrom).isPresent());
    }

    @Test
    public void declineFriendShipShouldBeFalse(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "mathew";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo).isPresent());
        assertTrue("valid declined friendship but was error", userTo.declineFriendShip(userFrom).isPresent());
        assertFalse("Invalid declined friendship but was ok", userTo.declineFriendShip(userFrom).isPresent());
    }

    @Test
    public void getFriendListShouldBeOK(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo1 = "mathew";
        final String passwordTo1 = "password";
        final String usernameTo2 = "jeremy";
        final String passwordTo2 = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo1 = new User.Builder().setName(usernameTo1).setPassword(passwordTo1).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo1).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo1).isPresent());
        assertTrue("valid declined friendship but was error", userTo1.acceptFriendShip(userFrom).isPresent());
        assertFalse("Invalid declined friendship but was ok", userTo1.acceptFriendShip(userFrom).isPresent());
        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo2).isPresent());
        assertTrue("valid declined friendship but was error", userTo2.acceptFriendShip(userFrom).isPresent());
        assertFalse("Invalid declined friendship but was ok", userTo2.acceptFriendShip(userFrom).isPresent());
        assertTrue("the user1 has to be friend but is not", userFrom.getFriendList().contains(userTo1));
        assertTrue("the user2 has to be friend but is not", userFrom.getFriendList().contains(userTo2));
    }

    @Test
    public void getFriendListShouldBeFalse(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo1 = "mathew";
        final String passwordTo1 = "password";
        final String usernameTo2 = "jeremy";
        final String passwordTo2 = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo1 = new User.Builder().setName(usernameTo1).setPassword(passwordTo1).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo1).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo1).isPresent());
        assertTrue("valid accept friendship but was error", userTo1.acceptFriendShip(userFrom).isPresent());
        assertFalse("Invalid accept friendship but was ok", userTo1.acceptFriendShip(userFrom).isPresent());
        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2).isPresent());
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo2).isPresent());
        assertTrue("the user1 has to be friend but is not", userFrom.getFriendList().contains(userTo1));
        assertFalse("the user2 is not a friend but he is in the list", userFrom.getFriendList().contains(userTo2));
    }

}
