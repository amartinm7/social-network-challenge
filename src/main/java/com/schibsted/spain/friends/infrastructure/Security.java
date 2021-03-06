package com.schibsted.spain.friends.infrastructure;

import com.schibsted.spain.friends.domain.auth.CustomAuthenticationProviderAdapter;
import com.schibsted.spain.friends.infrastructure.auth.CustomAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class Security extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(Security.class);

    @Autowired
    private CustomAuthenticationProviderAdapter customAuthenticationProviderAdapter;

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) {
        logger.info("configure AuthenticationManagerBuilder...");
        auth.authenticationProvider(customAuthenticationProviderAdapter);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("configure security...");
        http.addFilterBefore(new CustomAuthFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        logger.info("configure WebSecurity...");
        web.ignoring().antMatchers(HttpParams.URI_AUTH_WHITELIST);
    }
}
