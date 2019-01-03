package com.schibsted.spain.friends.infrastructure;

public class HttpParams {
    public static final String URI_LOGIN                    = "/login";
    public static final String URI_SIGNUP                   = "/signup";
    public static final String URI_FRIENDSHIP               = "/friendship";
    public static final String URI_FRIENDSHIP_REQUEST       = "/request";
    public static final String URI_FRIENDSHIP_ACCEPT        = "/accept";
    public static final String URI_FRIENDSHIP_DECLINE       = "/decline";
    public static final String URI_FRIENDSHIP_LIST          = "/list";
    public static final String URI_FRIENDSHIP_LIST_PENDING  = "/pending";


    public static final String URI_FRIENDSHIP_REQUEST_V1       = "{usernameFrom}/request/{usernameTo}";
    public static final String URI_FRIENDSHIP_ACCEPT_V1        = "{usernameFrom}/accept/{usernameTo}";
    public static final String URI_FRIENDSHIP_DECLINE_V1       = "{usernameFrom}/decline/{usernameTo}";
    public static final String URI_FRIENDSHIP_LIST_FRIENDS_V1  = "{username}/listFriends";
    public static final String URI_FRIENDSHIP_LIST_PENDING_V1  = "{username}/listPending";


    static final String[] URI_AUTH_WHITELIST = {
            URI_SIGNUP,
            "/",
            "/indexNew.html",
            "/actuator/**",
            "/src/**",
            "/v1/**",
            "/v2/**",
            "/v3/**",
            "/index.html",
            "/images/**",
            "/api/**",
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
    public static final String PASSWORD                     = "password";

}
