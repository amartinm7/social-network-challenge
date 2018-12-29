package com.schibsted.spain.friends.domain.auth;

import com.schibsted.spain.friends.domain.ports.UserQueryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProviderAdapter implements AuthenticationProvider {

    @Autowired
    private UserQueryPort userQueryPort;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken customAuth = (UsernamePasswordAuthenticationToken)authentication;
        String username = authentication.getName();
        String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : "";
        if (userQueryPort.isAuthorizatedUser(username, password)){
            return customAuth; // authorized users
        }
        return null; // not authorized users
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
