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
public class FriendshipLegacyControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    public void doSignUp(String username, String password, String expectedMessage) throws Exception {
        // given a user
        final String urlMethod = HttpParams.URI_SIGNUP;

        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (password);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, username);

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }


    public void doRequest(String usernameFrom, String passwordFrom, String usernameTo, String passwordTo) throws Exception {
        // given a user
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_REQUEST;

        // when
        doSignUp(usernameFrom,passwordFrom, usernameFrom);
        doSignUp(usernameTo,passwordTo, usernameTo);

        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordFrom);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameFrom, usernameTo);

        // Expected
        final String expectedMessage = usernameFrom;

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    public void doAccept(String usernameFrom, String passwordFrom, String usernameTo, String passwordTo) throws Exception {
        // given a user
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_ACCEPT;

        // when
        doRequest(usernameFrom, passwordFrom, usernameTo, passwordTo);

        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo, usernameFrom);

        final String expectedMessage = usernameTo;

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    public void doDecline(String usernameFrom, String passwordFrom, String usernameTo, String passwordTo) throws Exception {
        // given a user
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_DECLINE;

        // when
        doRequest(usernameFrom, passwordFrom, usernameTo, passwordTo);

        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo, usernameFrom);

        final String expectedMessage = usernameTo;

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void sendRightUsersRequestReturnOk() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX1";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ1";
        final String passwordTo = "password";

        doRequest(usernameFrom, passwordFrom, usernameTo, passwordTo);
    }

    @Test
    public void onlyExistsOneUserRequestReturnBadRequest() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX2";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ2";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_REQUEST;

        // when
        doSignUp(usernameFrom,passwordFrom, usernameFrom);
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordFrom);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameFrom, usernameTo);

        // expected
        final String expectedMessage = "400";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void sendRightUsersAcceptReturnOk() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX3";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ3";
        final String passwordTo = "password";

        // when
        doAccept( usernameFrom, passwordFrom, usernameTo, passwordTo);
    }

    @Test
    public void onlyExistsOneUserAcceptReturnBadRequest() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX4";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ4";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_ACCEPT;
        final String usernameUnknownUser = "UnknownUser";

        // when
        doRequest(usernameFrom, passwordFrom, usernameTo, passwordTo);

        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo, usernameUnknownUser);

        // expected
        final String expectedMessage = "400";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void sendRightUsersDeclineReturnOk() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX5";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ5";
        final String passwordTo = "password";

        // when
        doDecline( usernameFrom, passwordFrom, usernameTo, passwordTo);
    }

    @Test
    public void onlyExistsOneUserDeclineReturnBadRequest() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX6";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ6";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_DECLINE;
        final String usernameUnknownUser = "UnknownUser";

        // when
        doRequest(usernameFrom, passwordFrom, usernameTo, passwordTo);

        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo, usernameUnknownUser);

        // expected
        final String expectedMessage = "400";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }
    @Test
    public void invalidCredentialsReturnForbbiden() throws Exception {
        // given a user
        final String usernameFrom = "UnknownUser";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ6";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_DECLINE;
        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordFrom);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameFrom, usernameTo);

        // expected
        final String expectedMessage = "403";

        // then
        assertThat(this.restTemplate.postForObject(uri, request, String.class)).contains(expectedMessage);
    }

    @Test
    public void listFriends() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX7";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ7";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_LIST;

        // when
        doAccept( usernameFrom, passwordFrom, usernameTo, passwordTo);
        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo);

        // expected
        final String expectedMessage = usernameFrom;

        // then
        final ResponseEntity<String> response = this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        assertThat(response.getBody().contains(expectedMessage));
    }

    @Test
    public void listPendingFriends() throws Exception {
        // given a user
        final String usernameFrom = "johnnyX8";
        final String passwordFrom = "password";
        final String usernameTo = "johnnyZ8";
        final String passwordTo = "password";
        final String urlMethod =  HttpParams.URI_FRIENDSHIP + HttpParams.URI_FRIENDSHIP_LIST_PENDING;

        // when
        doRequest( usernameFrom, passwordFrom, usernameTo, passwordTo);
        // when
        final HttpEntity<MultiValueMap<String, String>> request = RestTemplateHelper.getHttpEntity (passwordTo);
        final URI uri = RestTemplateHelper.getURI(port, urlMethod, usernameTo);

        // expected
        final String expectedMessage = usernameFrom;

        // then
        final ResponseEntity<String> response = this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
        assertThat(response.getBody().contains(expectedMessage));
    }
}
