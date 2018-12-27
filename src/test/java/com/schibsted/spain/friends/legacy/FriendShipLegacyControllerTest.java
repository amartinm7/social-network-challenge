package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.User;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FriendShipLegacyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(FriendShipLegacyControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void signupEmptyUserShouldReturnBadRequest() throws Exception {
        // given
        final String username = "";
        final String pass = "rightPassword";
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList(username));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password",pass);
        // Then
        this.mockMvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("400")));
    }

    @Test
    public void signupEmptyPassShouldReturnBadRequest() throws Exception {
        // given
        final String username = "Johnny";
        final String pass = "";
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList(username));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password",pass);
        // Then
        this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("400")));
    }

    @Test
    public void signupBadUserShouldReturnBadRequest() throws Exception {
        // given
        final String username = "john";
        final String pass = "rightPassword";
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList(username));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password",pass);
        // Then
        this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("400")));
    }

    public void signupBadPassShouldReturnBadRequest() throws Exception {
        // given
        final String username = "johnny";
        final String pass = "short";
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList(username));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password",pass);
        // Then
        this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("400")));
    }

    @Test
    public void signupReturnOk() throws Exception {
        // given
        final String username = "johnny";
        final String pass = "passXXXXX";
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList(username));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password",pass);
        // Expected
        final User userExpected = new User.Builder().setName(username).setPassword(pass).build();
        // Then
        this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userExpected.toString())));
    }



}
