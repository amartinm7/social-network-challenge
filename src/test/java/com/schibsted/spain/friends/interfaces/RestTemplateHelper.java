package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.Collections;

public class RestTemplateHelper {

    final static String DOMAIN = "http://localhost:";

    public static HttpEntity<MultiValueMap<String, String>> getHttpEntity (String username, String password) throws Exception{
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpParams.X_PASSWORD,password);

        final MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.put("bodyParam1", Collections.singletonList("putParamXHereIfYouNeedIt"));

        final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    public static URI getURI (int port , String urlMethod, String username) throws Exception{
        final String url = DOMAIN + port + urlMethod;
        final URI uriTemp = new URL(url).toURI();
        final URI uri = UriComponentsBuilder
                .fromUri(uriTemp)
                .queryParam(HttpParams.USER_NAME, username)
                .build()
                .toUri();
        return uri;
    }


    public static URI getURI (int port , String urlMethod, String usernameFrom, String usernameTo) throws Exception{
        final String url = DOMAIN + port + urlMethod;
        final URI uriTemp = new URL(url).toURI();
        final URI uri = UriComponentsBuilder
                .fromUri(uriTemp)
                .queryParam(HttpParams.USER_NAME_FROM, usernameFrom)
                .queryParam(HttpParams.USER_NAME_TO, usernameTo)
                .build()
                .toUri();
        return uri;
    }

}
