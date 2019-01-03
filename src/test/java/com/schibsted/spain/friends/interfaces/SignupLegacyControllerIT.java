package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    private void doSignUp(String username, String password, String expectedMessage) throws Exception {
        // given a url
        final String urlMethod = HttpParams.URI_SIGNUP;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (password);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, username);

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    private void doLogin(String username, String password, String expectedMessage) throws Exception {
        // given a url
        final String urlMethod =  HttpParams.URI_LOGIN;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity ("");
        final URI uri = RestTemplateHelper.getURIForLogin(port, urlMethod, username, password);

        // then
        final ResponseEntity<String> response = this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        assertThat(response.getBody().contains(expectedMessage));
    }

    @Test
    public void signupReturnOk() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";

        // when
        doSignUp(username, password, username);
    }

    @Test
    public void signupInvalidNameReturnBadRequest() throws Exception {
        // given a user
        final String username = "john";
        final String password = "password";

        // expected
        final String expectedMessage = "400";

        // when
        doSignUp(username, password, expectedMessage);
    }

    @Test
    public void signupInvalidPassReturnBadRequest() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "pass";
        // expected
        final String expectedMessage = "400";

        // when
        doSignUp(username, password, expectedMessage);
    }

    @Test
    public void loginReturnOk() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";
        // when
        doSignUp(username, password, username);
        doLogin(username, password, username);
    }

    @Test
    public void loginInvalidUsernameReturnBadRequest() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";
        final String wrongUsername = "wrongname";
        // expected
        final String expectedMessage = "400";
        // when
        doSignUp(username, password, username);
        doLogin(wrongUsername, password, expectedMessage);
    }

    @Test
    public void loginInvalidPassReturnBadRequest() throws Exception {
        // given a user
        final String username = "johnnyX";
        final String password = "password";
        final String wrongPassword = "wrongpassword";
        // expected
        final String expectedMessage = "400";
        // when
        doSignUp(username, password, username);
        doLogin(username, wrongPassword, expectedMessage);
    }

}
