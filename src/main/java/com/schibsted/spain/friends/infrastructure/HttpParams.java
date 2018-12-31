package com.schibsted.spain.friends.infrastructure;

public class HttpParams {
    public static final String URI_SIGNUP                   = "/signup";
    public static final String URI_FRIENDSHIP               = "/friendship";
    public static final String URI_FRIENDSHIP_REQUEST       = "/request";
    public static final String URI_FRIENDSHIP_ACCEPT        = "/accept";
    public static final String URI_FRIENDSHIP_DECLINE       = "/decline";
    public static final String URI_FRIENDSHIP_LIST          = "/list";

    static final String[] URI_AUTH_WHITELIST = {
            URI_SIGNUP,
            "/",
            "/login.html",
            "/src/**",
            "/v1/**",
            "/v2/**",
            "/index.html",
            "/images/**",
            "/api/**",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};


    public static final String X_PASSWORD                   = "X-Password";
    public static final String USER_NAME                    = "username";
    public static final String USER_NAME_FROM               = "usernameFrom";
    public static final String USER_NAME_TO                 = "usernameTo";


}
