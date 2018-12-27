package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomStorageProviderAdapterTest {

    @Test
    public void saveRightUserShouldBeOK(){
        // given
        final String username = "johnny";
        final String password = "password";
        final User user = new User.Builder().setName(username).setPassword(password).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(user);
        assertNotNull("The user was not saved properly", user);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void saveTheSameUserShouldBeFalse(){
        // given
        final String username = "johnny";
        final String password = "password";
        final User user = new User.Builder().setName(username).setPassword(password).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(user);
        assertNotNull("The user was not saved properly", user);
        // Throws exception
        service.save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void requestFriendShipUserFromNotValid(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyZ";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userTo);
        // Throws exception
        service.requestFriendship(userFrom, usernameZ);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void requestFriendShipUserToNotValid(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyZ";
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        // Throws exception
        service.requestFriendship(userFrom, usernameZ);
    }

    @Test
    public void requestFriendShipShouldBeOk(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyZ";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userTo);
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameZ));
    }

    @Test
    @Ignore
    public void acceptFriendShipShouldBeOk(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyO";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userTo);
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameZ));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameZ));
        assertTrue("The accept was not saved properly", service.acceptFriendship(userFrom, usernameZ));
        assertFalse("The accept was saved previously and we can't save it another time", service.acceptFriendship(userFrom, usernameZ));
    }

    @Test
    public void acceptFriendShipShouldBeFalse(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyO";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userTo);
        assertFalse("The accept was not saved properly", service.acceptFriendship(userFrom, usernameZ));
        assertFalse("The accept was saved previously and we can't save it another time", service.acceptFriendship(userFrom, usernameZ));
    }

    @Test
    @Ignore
    public void declineFriendShipShouldBeOk(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyO";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userTo);
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameZ));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameZ));
        assertTrue("Removing the request was not done properly", service.declineFriendship(userFrom, usernameZ));
        assertFalse("The request was removed previously and it's not possible removing it another time", service.declineFriendship(userFrom, usernameZ));
    }

    @Test
    public void declineFriendShipShouldBeFalse(){
        // given
        final String usernameX = "johnnyX";
        final String passwordX = "password";
        final User userFrom = new User.Builder().setName(usernameX).setPassword(passwordX).build();
        final String usernameZ = "johnnyO";
        final String passwordZ = "password";
        final User userTo = new User.Builder().setName(usernameZ).setPassword(passwordZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userTo);
        assertFalse("The accept was not saved properly", service.declineFriendship(userFrom, usernameZ));
        assertFalse("The accept was saved previously and we can't save it another time", service.declineFriendship(userFrom, usernameZ));
    }

    @Test
    @Ignore
    public void listFriendShipShouldBeOK(){
        // given
        final String usernameFrom = "johnnyX";
        final String passwordFrom = "password";
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        final String usernameToY = "mathew";
        final String passwordToY = "password";
        final User userToY = new User.Builder().setName(usernameToY).setPassword(passwordToY).build();
        final String usernameToZ = "jeremy";
        final String passwordToZ = "password";
        final User userToZ = new User.Builder().setName(usernameToZ).setPassword(passwordToZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userToY);
        service.save(userToZ);
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameToY));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameToY));
        assertTrue("The accept was not saved properly", service.acceptFriendship(userFrom, usernameToY));
        assertFalse("The accept was saved previously and we can't save it another time", service.acceptFriendship(userFrom, usernameToY));
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameToZ));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameToZ));
        assertTrue("The accept was not saved properly", service.acceptFriendship(userFrom, usernameToZ));
        assertFalse("The accept was saved previously and we can't save it another time", service.acceptFriendship(userFrom, usernameToZ));
        assertTrue("The userY is not in the list", service.listFriends(userFrom).contains(userToY));
        assertTrue("The userZ is not in the list", service.listFriends(userFrom).contains(userToZ));
    }

    @Test
    @Ignore
    public void listFriendShipShouldBeFalse(){
        // given
        final String usernameFrom = "johnnyX";
        final String passwordFrom = "password";
        final User userFrom = new User.Builder().setName(usernameFrom).setPassword(passwordFrom).build();
        final String usernameToY = "mathew";
        final String passwordToY = "password";
        final User userToY = new User.Builder().setName(usernameToY).setPassword(passwordToY).build();
        final String usernameToZ = "jeremy";
        final String passwordToZ = "password";
        final User userToZ = new User.Builder().setName(usernameToZ).setPassword(passwordToZ).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        service.save(userFrom);
        service.save(userToY);
        service.save(userToZ);
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameToY));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameToY));
        assertTrue("The accept was not saved properly", service.acceptFriendship(userFrom, usernameToY));
        assertFalse("The accept was saved previously and we can't save it another time", service.acceptFriendship(userFrom, usernameToY));
        assertTrue("The request was not saved properly", service.requestFriendship(userFrom, usernameToZ));
        assertFalse("The request was saved previously and we can't save it another time", service.requestFriendship(userFrom, usernameToZ));
        assertTrue("The userY is not in the list", service.listFriends(userFrom).contains(userToY));
        assertFalse("The userZ is in the list but it shouldn't be", service.listFriends(userFrom).contains(userToZ));
    }

}
