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




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username = "";
        final String xPassword = request.getHeader(HttpParams.X_PASSWORD);
        if (request.getRequestURI().contains(HttpParams.URI_SIGNUP)
                || (request.getRequestURI().contains(HttpParams.URI_FRIENDSHIP_LIST))){
            username = request.getParameter(HttpParams.USER_NAME);
        } else if (request.getRequestURI().contains(HttpParams.URI_FRIENDSHIP)){
            username = request.getParameter(HttpParams.USER_NAME_FROM);
        }
        // Create our Authentication and let Spring know about it
        Authentication auth = new UsernamePasswordAuthenticationToken(username, xPassword);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}
