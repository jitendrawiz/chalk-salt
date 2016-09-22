package com.chalk.salt.api.security.oauth;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuthToken implements AuthenticationToken {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -269246695644614409L;

    /** The Constant OAUTH_CONSUMER_KEY. */
    public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";

    /** The credentials. */
    private final OAuthCredentials credentials;

    /** The principal. */
    private final String principal;

    /**
     * Instantiates a new o auth token.
     * 
     * @param httpMethod the http method
     * @param url the url
     * @param parameters the parameters
     */
    public OAuthToken(final String httpMethod, final String url, final Map<String, String> parameters) {
        credentials = new OAuthCredentials(httpMethod, url, parameters);
        principal = parameters.get(OAUTH_CONSUMER_KEY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return principal;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return credentials;
    }
}
