package com.schibsted.spain.friends.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void buildEmptyUserShouldThrowError(){
        // given
        final String username = "";
        final String password = "password";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildEmptyPassShouldThrowError(){
        // given
        final String username = "johnny";
        final String password = "";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildShortNameShouldThrowError(){
        // given
        final String username = "john";
        final String password = "password";
        // then
        new User.Builder().setName(username).setPassword(password).build();
    }

    @Test
    public void buildShortPassShouldThrowError(){
        // given
        final String username = "johnny";
        final String password = "password";
        // then
        User user = new User.Builder().setName(username).setPassword(password).build();
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
        assertTrue("Not valid request for friendship", userFrom.requestFriendShip(userTo));
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
        assertTrue("Not valid request for friendship", userFrom.requestFriendShip(userTo));
        assertFalse("Not valid request for friendship", userFrom.requestFriendShip(userTo));
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
        assertTrue("Not valid request for friendship", userFrom.requestFriendShip(userTo));
        assertFalse("Not valid request for friendship", userFrom.requestFriendShip(userTo));
        assertTrue("Not accepted friendship", userFrom.acceptFriendShip(userTo));
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
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo));
        assertTrue("valid accepted friendship but was error", userFrom.acceptFriendShip(userTo));
        assertFalse("Invalid accepted friendship but was ok", userFrom.acceptFriendShip(userTo));
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
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo));
        assertTrue("valid declined friendship but was error", userFrom.declineFriendShip(userTo));
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
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo));
        assertTrue("valid declined friendship but was error", userFrom.declineFriendShip(userTo));
        assertFalse("Invalid declined friendship but was ok", userFrom.declineFriendShip(userTo));
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
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo1));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo1));
        assertTrue("valid declined friendship but was error", userFrom.acceptFriendShip(userTo1));
        assertFalse("Invalid declined friendship but was ok", userFrom.acceptFriendShip(userTo1));
        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo2));
        assertTrue("valid declined friendship but was error", userFrom.acceptFriendShip(userTo2));
        assertFalse("Invalid declined friendship but was ok", userFrom.acceptFriendShip(userTo2));
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
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo1));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo1));
        assertTrue("valid declined friendship but was error", userFrom.acceptFriendShip(userTo1));
        assertFalse("Invalid declined friendship but was ok", userFrom.acceptFriendShip(userTo1));
        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2));
        assertFalse("Invalid request for friendship but was ok", userFrom.requestFriendShip(userTo2));
        assertTrue("the user1 has to be friend but is not", userFrom.getFriendList().contains(userTo1));
        assertFalse("the user2 is not a friend but he is in the list", userFrom.getFriendList().contains(userTo2));
    }

}
