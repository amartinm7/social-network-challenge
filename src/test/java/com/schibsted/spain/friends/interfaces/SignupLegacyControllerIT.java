package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class SignupLegacyControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signupReturnOk() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";
        final String urlMethod = HttpParams.URI_SIGNUP;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (username, password);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, username);

        // expected
        final String expectedMessage = username;

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void signupInvalidNameReturnBadRequest() throws Exception {
        // given a user
        final String username = "john";
        final String password = "password";
        final String urlMethod = HttpParams.URI_SIGNUP;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (username, password);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, username);

        // expected
        final String expectedMessage = "400";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void signupInvalidPassReturnBadRequest() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "pass";
        final String urlMethod = HttpParams.URI_SIGNUP;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (username, password);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, username);

        // expected
        final String expectedMessage = "400";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

}
