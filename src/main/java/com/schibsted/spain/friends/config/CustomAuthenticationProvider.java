package com.schibsted.spain.friends.config;

import com.schibsted.spain.friends.persistence.CustomStorageProviderAdapter;
import com.schibsted.spain.friends.persistence.CustomStorageProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomStorageProviderService customStorageProviderService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info(">>>CustomAuthenticationProvider..." + authentication.toString());
        UsernamePasswordAuthenticationToken customAuth = (UsernamePasswordAuthenticationToken)authentication;
        String username = authentication.getName();
        String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : "";

        if (customStorageProviderService.isAuthorizatedUser(username,password)){
            return customAuth;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
