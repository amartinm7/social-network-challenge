package com.schibsted.spain.friends.domain.adapters;

import com.schibsted.spain.friends.domain.ports.UserCommandPort;
import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAdaptersTest {

    @Autowired
    private UserCommandPort userCommandPort;

    @Autowired
    private UserQueryPort userQueryPort;

    private final String usernameFrom = "johnnyX";
    private final String passwordFrom = "password";
    private final String usernameTo = "johnnyZ";
    private final String passwordTo = "password";
    private final String usernameTo2 = "johnnyO";
    private final String passwordTo2 = "password";

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
        assertThrows (IllegalArgumentException.class,() ->userCommandPort.save(usernameFrom, passwordFrom), "The user was saved previously");

    }

    @Test
    public void requestFriendShipUserFromNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertThrows (IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo), "The userFrom, doesn't exists. The userFrom wasn't saved previously");
    }

    @Test
    public void requestFriendShipUserToNotValid(){
        // given a user
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertThrows (IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo), "The userTo, doesn't exists. The userTo wasn't saved previously");
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
        assertThrows (IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.acceptFriendship(usernameFrom, usernameTo), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
    }

    @Test
    public void acceptFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.acceptFriendship(usernameFrom, usernameTo), "There's no any previous request to accept");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.acceptFriendship(usernameTo, usernameFrom), "There's no any previous request to accept");
    }

    @Test
    public void declineFriendShipShouldBeOk(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The requestFriendship was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.declineFriendship(usernameTo, usernameFrom).isPresent(), "The declineFriendship was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.declineFriendship(usernameFrom, usernameTo), "The declineFriendship was done previously, two declineFriendship are not possible.");
    }

    @Test
    public void declineFriendShipShouldBeFalse(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.declineFriendship(usernameFrom, usernameTo), "There's no any previous request to accept");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.declineFriendship(usernameTo, usernameFrom), "There's no any previous request to accept");
    }

    @Test
    public void listFriendShipShouldBeOK(){
        // given some users
        // Then
        assertTrue(userCommandPort.save(usernameFrom, passwordFrom).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo, passwordTo).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.save(usernameTo2, passwordTo2).isPresent(), "The user was not saved properly" );
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo).isPresent(), "The request was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.acceptFriendship(usernameFrom, usernameTo), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userCommandPort.requestFriendship(usernameFrom, usernameTo2).isPresent(), "The request was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.requestFriendship(usernameFrom, usernameTo2), "The requestFriendship accepted previously, two requests are not possible.");
        assertTrue(userCommandPort.acceptFriendship(usernameTo2, usernameFrom).isPresent(), "The acceptFriendship was not saved properly");
        assertThrows(IllegalArgumentException.class,() ->userCommandPort.acceptFriendship(usernameFrom, usernameTo2), "The acceptFriendship was done previously, two acceptFriendship are not possible.");
        assertTrue(userQueryPort.listFriends(usernameFrom).size() == 2, "All the users are not in the list");
    }
}
