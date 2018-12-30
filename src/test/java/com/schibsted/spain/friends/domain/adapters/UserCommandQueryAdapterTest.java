package com.schibsted.spain.friends.domain.adapters;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCommandQueryAdapterTest {

    @Autowired
    private UserCommandPort userCommandPort;

    @Autowired
    private UserQueryPort userQueryPort;

    final String usernameFrom = "johnnyX";
    final String passwordFrom = "password";
    final String usernameTo = "johnnyZ";
    final String passwordTo = "password";
    final String usernameTo2 = "johnnyO";
    final String passwordTo2 = "password";

    @Before
    public void setup(){
        userCommandPort.remove(usernameFrom);
        userCommandPort.remove(usernameTo);
        userCommandPort.remove(usernameTo2);
    }

    @Test
    public void saveRightUserShouldBeOK(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly");
    }

    @Test
    public void saveTheSameUserShouldBeFalse(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly");
        assertFalse(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was saved previously" );
    }

    @Test
    public void requestFriendShipUserFromNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The userFrom, doesn't exists. The userFrom wasn't saved previously");
    }

    @Test
    public void requestFriendShipUserToNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The userTo, doesn't exists. The userTo wasn't saved previously");
    }

    @Test
    public void requestFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship was not saved properly");
    }

    @Test
    public void acceptFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo).isPresent(), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
    }

    @Test
    public void acceptFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo).isPresent(), "There's no any previous request to accept");
        assertFalse(userCommandPort.acceptFriendship(usernameTo, usernameFrom).isPresent(), "There's no any previous request to accept");
    }

    @Test
    public void declineFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.declineFriendship(usernameTo, usernameFrom).isPresent(), "The declineFriendship was not saved properly");
        assertFalse(userCommandPort.declineFriendship(usernameFrom, usernameTo).isPresent(), "The declineFriendship was done previously, two declineFriendship are not possible.");
    }

    @Test
    public void declineFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertFalse(userCommandPort.declineFriendship(usernameFrom, usernameTo).isPresent(), "There's no any previous request to accept");
        assertFalse(userCommandPort.declineFriendship(usernameTo, usernameFrom).isPresent(), "There's no any previous request to accept");
    }

    @Test
    public void listFriendShipShouldBeOK(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo2, passwordTo2).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The request was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo).isPresent(), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo2).isPresent(), "The request was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo2).isPresent(), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo2, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo2).isPresent(), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userQueryPort.listFriends(usernameFrom).size() == 2, "All the users are not in the list");
    }
}
