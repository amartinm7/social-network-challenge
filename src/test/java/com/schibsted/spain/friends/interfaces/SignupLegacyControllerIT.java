package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
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
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
public class SignupLegacyControllerIT {
    private static final Logger logger = LoggerFactory.getLogger(SignupLegacyControllerIT.class);
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signupReturnOk() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";

        // when
        final String url = "http://localhost:" + port + "/signup";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpParams.X_PASSWORD,password);

        final MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.put("bodyParam1", Collections.singletonList("bodyParam1xxx"));

        URI uri = new URL(url).toURI();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .queryParam(HttpParams.USER_NAME, username)
                .build()
                .toUri();

        final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // then
        final String expectedMessage = username;
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void signupReturnBadRequest() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "pass";

        // when
        final String url = "http://localhost:" + port + "/signup";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpParams.X_PASSWORD,password);

        final MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.put("bodyParam1", Collections.singletonList("bodyParam1xxx"));

        URI uri = new URL(url).toURI();
        uri = UriComponentsBuilder
                .fromUri(uri)
                .queryParam(HttpParams.USER_NAME, username)
                .build()
                .toUri();

        final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // then
        final String expectedMessage = "400";
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

}
