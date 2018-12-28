package com.schibsted.spain.friends.domain.adapters;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly");
    }

    @Test
    public void saveTheSameUserShouldBeFalse(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly");
        assertFalse(userCommandPort.save(usernameFrom, passwordFrom), "The user was saved previously" );
    }

    @Test
    public void requestFriendShipUserFromNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertFalse( userCommandPort.requestFriendship(usernameFrom, usernameTo), "The userFrom, doesn't exists. The userFrom wasn't saved previously");
    }

    @Test
    public void requestFriendShipUserToNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertFalse( userCommandPort.requestFriendship(usernameFrom, usernameTo), "The userTo, doesn't exists. The userTo wasn't saved previously");
    }

    @Test
    public void requestFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship was not saved properly");
    }

    @Test
    public void acceptFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
    }

    @Test
    public void acceptFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertFalse( userCommandPort.acceptFriendship(usernameFrom, usernameTo), "There's no any previous request to accept");
        assertFalse( userCommandPort.acceptFriendship(usernameTo, usernameFrom), "There's no any previous request to accept");
    }

    @Test
    public void declineFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.declineFriendship(usernameTo, usernameFrom), "The declineFriendship was not saved properly");
        assertFalse(userCommandPort.declineFriendship(usernameFrom, usernameTo), "The declineFriendship was done previously, two declineFriendship are not possible.");
    }

    @Test
    public void declineFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertFalse(userCommandPort.declineFriendship(usernameFrom, usernameTo), "There's no any previous request to accept");
        assertFalse(userCommandPort.declineFriendship(usernameTo, usernameFrom), "There's no any previous request to accept");
    }

    @Test
    public void listFriendShipShouldBeOK(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo2, passwordTo2), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The request was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo2), "The request was not saved properly");
        assertFalse(userCommandPort.requestFriendship(usernameFrom, usernameTo2), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo2, usernameFrom), "The acceptFriendship was not saved properly");
        assertFalse(userCommandPort.acceptFriendship(usernameFrom, usernameTo2), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userQueryPort.listFriends(usernameFrom).size() == 2, "All the users are not in the list");
    }
}
