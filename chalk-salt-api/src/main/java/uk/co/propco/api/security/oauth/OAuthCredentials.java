package com.chalk.salt.api.security.oauth;

import java.util.Map;

/**
 * The Class OAuthCredentials.
 * 
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 * 
 */
public class OAuthCredentials {

    /** The http method. */
    private String httpMethod;

    /** The url. */
    private String url;

    /** The query parameters. */
    private Map<String, String> queryParameters;

    /**
     * Instantiates a new o auth credentials.
     * 
     * @param httpMethod the http method
     * @param url the url
     * @param parameters the parameters
     */
    public OAuthCredentials(final String httpMethod, final String url, final Map<String, String> parameters) {
        this.httpMethod = httpMethod;
        this.url = url;
        queryParameters = parameters;
    }

    /**
     * Gets the http method.
     * 
     * @return the http method
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * Sets the http method.
     * 
     * @param httpMethod the new http method
     */
    public void setHttpMethod(final String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * Gets the url.
     * 
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     * 
     * @param url the new url
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Gets the query parameters.
     * 
     * @return the query parameters
     */
    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    /**
     * Sets the query parameters.
     * 
     * @param queryParameters the query parameters
     */
    public void setQueryParameters(final Map<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

}
