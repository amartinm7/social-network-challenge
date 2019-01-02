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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
    }

    @Test
    public void twoRequestOverTheSameUserShouldBeFalse(){
        // given
        final String usernameFrom = "johnny";
        final String passwordFrom = "password";
        final String usernameTo = "johnny";
        final String passwordTo = "password";
        // then
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        assertNotNull("The userFrom is null", userFrom);
        final User userTo = new User.Builder().setName(usernameTo).setPassword(passwordTo).build();
        assertNotNull("The userTo is null", userFrom);
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
        assertTrue("valid accepted friendship but was error", userTo.acceptFriendShip(userFrom).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userTo.acceptFriendShip(userFrom), "Not valid accept for friendship");

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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo), "Not valid request for friendship");
        assertTrue("valid declined friendship but was error", userTo.declineFriendShip(userFrom).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userTo.declineFriendShip(userFrom), "Not valid decline for friendship");

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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo1), "Not valid request for friendship");
        assertTrue("valid declined friendship but was error", userTo1.acceptFriendShip(userFrom).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userTo1.acceptFriendShip(userFrom), "Not valid accept for friendship");

        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo2), "Not valid request for friendship");
        assertTrue("valid declined friendship but was error", userTo2.acceptFriendShip(userFrom).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userTo2.acceptFriendShip(userFrom), "Not valid accept for friendship");
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
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo1), "Not valid request for friendship");
        assertTrue("valid accept friendship but was error", userTo1.acceptFriendShip(userFrom).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userTo1.acceptFriendShip(userFrom), "Not valid accept for friendship");

        final User userTo2 = new User.Builder().setName(usernameTo2).setPassword(passwordTo2).build();
        assertNotNull("The userTo is null", userFrom);
        assertTrue("valid request for friendship but was error", userFrom.requestFriendShip(userTo2).isPresent());
        assertThrows (IllegalArgumentException.class,() -> userFrom.requestFriendShip(userTo2), "Not valid request for friendship");
        assertTrue("the user1 has to be friend but is not", userFrom.getFriendList().contains(userTo1));
        assertFalse("the user2 is not a friend but he is in the list", userFrom.getFriendList().contains(userTo2));
    }
}
