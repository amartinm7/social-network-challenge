package com.schibsted.spain.friends.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthFilter extends OncePerRequestFilter {

    private static final String X_PASSWORD                  = "X-Password";
    private static final String USER_NAME                   = "username";
    private static final String USER_NAME_FROM              = "usernameFrom";
    private static final String REQUEST_URI_SIGNUP          = "/signup";
    private static final String REQUEST_URI_FRIENDSHIP      = "/friendship";
    private static final String REQUEST_URI_FRIENDSHIP_LIST = "/friendship/list";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username = "";
        final String xPassword = request.getHeader(X_PASSWORD);
        if (request.getRequestURI().contains(REQUEST_URI_SIGNUP)
                || (request.getRequestURI().contains(REQUEST_URI_FRIENDSHIP_LIST))){
            username = request.getParameter(USER_NAME);
        } else if (request.getRequestURI().contains(REQUEST_URI_FRIENDSHIP)){
            username = request.getParameter(USER_NAME_FROM);
        }
        // Create our Authentication and let Spring know about it
        Authentication auth = new UsernamePasswordAuthenticationToken(username, xPassword);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}
