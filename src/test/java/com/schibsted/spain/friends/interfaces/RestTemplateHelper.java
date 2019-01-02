package com.schibsted.spain.friends.interfaces;

import com.schibsted.spain.friends.infrastructure.HttpParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.Collections;

public class RestTemplateHelper {

    final static String DOMAIN = "http://localhost:";

    public static HttpEntity<MultiValueMap<String, String>> getHttpEntity (String password) throws Exception{
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!StringUtils.isEmpty(password)) {
            headers.add(HttpParams.X_PASSWORD, password);
        }

        final MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.put("bodyParam1", Collections.singletonList("putParamXHereIfYouNeedIt"));

        final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return request;
    }

    public static URI getURI (int port , String urlMethod, String queryParamUsername) throws Exception{
        final String url = DOMAIN + port + urlMethod;
        final URI uriTemp = new URL(url).toURI();
        final URI uri = UriComponentsBuilder
                .fromUri(uriTemp)
                .queryParam(HttpParams.USER_NAME, queryParamUsername)
                .build()
                .toUri();
        return uri;
    }


    public static URI getURI (int port , String urlMethod, String queryParamUsernameFrom, String queryParamUsernameTo) throws Exception{
        final String url = DOMAIN + port + urlMethod;
        final URI uriTemp = new URL(url).toURI();
        final URI uri = UriComponentsBuilder
                .fromUri(uriTemp)
                .queryParam(HttpParams.USER_NAME_FROM, queryParamUsernameFrom)
                .queryParam(HttpParams.USER_NAME_TO, queryParamUsernameTo)
                .build()
                .toUri();
        return uri;
    }

    public static URI getURIForLogin (int port , String urlMethod, String queryParamUsernameFrom, String password) throws Exception{
        final String url = DOMAIN + port + urlMethod;
        final URI uriTemp = new URL(url).toURI();
        final URI uri = UriComponentsBuilder
                .fromUri(uriTemp)
                .queryParam(HttpParams.USER_NAME_FROM, queryParamUsernameFrom)
                .queryParam(HttpParams.PASSWORD, password)
                .build()
                .toUri();
        return uri;
    }

}
