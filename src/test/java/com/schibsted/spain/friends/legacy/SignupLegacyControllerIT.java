package com.schibsted.spain.friends.legacy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupLegacyControllerIT {
    private static final Logger logger = LoggerFactory.getLogger(SignupLegacyControllerIT.class);
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signupReturnOk() throws Exception {
        final String url = "http://localhost:" + port + "/signup";
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Password","secureXXX");
        final MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.put("bodyParam1", Collections.singletonList("bodyParam1xxx"));
        URI uri = new URL(url).toURI();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .queryParam("username", "john5")
                .build()
                .toUri();
        final HttpEntity<MultiValueMap<String, String>> request
                = new HttpEntity<>(map, headers);
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains("User{name='john5', password='secureXXX'}");
    }

}
