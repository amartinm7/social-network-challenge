package com.schibsted.spain.friends.infrastructure.auth;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
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

        final String xPassword = request.getHeader(HttpParams.X_PASSWORD);

        String username = request.getParameter(HttpParams.USER_NAME);
        if (StringUtils.isEmpty(username)) {
            username = request.getParameter(HttpParams.USER_NAME_FROM);
        }

        // FOR SUPPORTING THE NEW CONTROLLER URI VERSIONS
        if (StringUtils.isEmpty(username)){
            final String[] tokens = request.getRequestURI().split("/");
            if (tokens.length > 2) {
                username = request.getRequestURI().split("/")[2];
            }
        }

        // Create our Authentication and let Spring know about it
        Authentication auth = new UsernamePasswordAuthenticationToken(username, xPassword);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}
