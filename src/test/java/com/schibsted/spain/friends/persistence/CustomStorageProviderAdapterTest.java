package com.schibsted.spain.friends.persistence;

import com.schibsted.spain.friends.model.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomStorageProviderAdapterTest {

    @Test
    public void saveUserShouldBeTrue(){
        // given
        final String username = "johnny";
        final String password = "password";
        final User user = new User.Builder().setName(username).setPassword(password).build();
        // Expected
        final boolean savedExpected = true;
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        final boolean obtained = service.save(user);
        assertEquals("The user was not saved properly", savedExpected, obtained);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveTheSameUserShouldBeFalse(){
        // given
        final String username = "johnny";
        final String password = "password";
        final User user = new User.Builder().setName(username).setPassword(password).build();
        // Then
        final CustomStorageProviderService service = new CustomStorageProviderAdapter();
        final boolean firstSaved = service.save(user);
        assertTrue("The user was not saved properly", firstSaved);
        final boolean secondSaved = service.save(user);
        assertFalse("The user was not saved properly", secondSaved);
    }

}
