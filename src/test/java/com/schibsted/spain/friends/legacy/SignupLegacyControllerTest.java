package com.schibsted.spain.friends.legacy;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SignupLegacyControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(SignupLegacyControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void signupReturnOkk() throws Exception {
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList("john"));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password","passXXX");

        this.mockMvc.perform(get("/signup2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(user)
                        .headers(httpHeaders)
                ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("200")));
    }

    @Test
    public void signupReturnOk() throws Exception {
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put("username", Collections.singletonList("john"));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Password","passXXX");
        this.mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status_code\":2")));
    }

}
