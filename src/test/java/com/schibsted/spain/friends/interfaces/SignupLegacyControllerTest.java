package com.schibsted.spain.friends.interfaces;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SignupLegacyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(SignupLegacyControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signupEmptyUserShouldReturnBadRequest() throws Exception {
        // given
        final String username = "";
        final String password = "password";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "400";

        // Then
        this.mockMvc.perform(post(HttpParams.URI_SIGNUP)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedMessage)));
    }

    @Test
    public void signupEmptyPassShouldReturnBadRequest() throws Exception {
        // given
        final String username = "Johnny";
        final String password = "";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "400";

        // Then
        this.mockMvc.perform(post(HttpParams.URI_SIGNUP)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedMessage)));
    }

    @Test
    public void signupBadUserShouldReturnBadRequest() throws Exception {
        // given
        final String username = "John";
        final String password = "password";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "400";

        // Then
        this.mockMvc.perform(post(HttpParams.URI_SIGNUP)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedMessage)));
    }

    public void signupBadPassShouldReturnBadRequest() throws Exception {
        // given
        final String username = "Johnny";
        final String password = "short";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "400";

        // Then
        this.mockMvc.perform(post(HttpParams.URI_SIGNUP)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(expectedMessage)));
    }

    @Test
    public void signupReturnOk() throws Exception {
        // given
        final String username = "Johnny";
        final String password = "password";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = username;

        // Then
        this.mockMvc.perform(post(HttpParams.URI_SIGNUP)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedMessage)));
    }



}
