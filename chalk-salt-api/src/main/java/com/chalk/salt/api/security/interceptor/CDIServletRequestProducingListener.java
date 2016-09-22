package com.chalk.salt.api.security.interceptor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.docs.swagger.ApiOriginFilter;
import com.chalk.salt.api.security.oauth.OAuthToken;
import com.chalk.salt.api.util.ApiRequestWrapper;


@WebListener
public class CDIServletRequestProducingListener implements ServletRequestListener {

    /** The Constant OAUTH_SIGNATURE_PARAM. */
    private static final String OAUTH_SIGNATURE_PARAM = "oauth_signature";

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Subject subject;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestListener#requestInitialized(javax.servlet.ServletRequestEvent)
     */
    @Override
    public void requestInitialized(final ServletRequestEvent sre) {

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletRequestListener#requestDestroyed(javax.servlet.ServletRequestEvent)
     */
    @Override
    public void requestDestroyed(final ServletRequestEvent sre) {
        // SERVLET_REQUESTS.remove();
    }

    /**
     * Obtain.
     *
     * @return the http servlet request
     */
    @Produces
    @Named("webRequest")
    private HttpServletRequest obtain() {
        return ApiOriginFilter.getCurrentHttpRequest();
    }

    /**
     * Creates the token.
     *
     * @return the authentication token
     */
    @Produces
    private AuthenticationToken createToken() {
        final HttpServletRequest webRequest = obtain();
        if (!subject.isAuthenticated()) {
            final Map<String, String> oauthParameters = getOAuthParameters(webRequest);
            return new OAuthToken(webRequest.getMethod(), webRequest.getRequestURL().toString(), oauthParameters);
        }
        return null;
    }

    /**
     * Gets the o auth parameters.
     *
     * @param webRequest the web request
     * @return the o auth parameters
     */
    private Map<String, String> getOAuthParameters(final HttpServletRequest webRequest) {
        final String authorizationHeader = webRequest.getHeader("Authorization");
        Map<String, String> oauthParameters = null;
        if (StringUtils.contains(authorizationHeader, OAUTH_SIGNATURE_PARAM)) {
            oauthParameters = getAuthParamsFromHeader(authorizationHeader);
        } else {
            oauthParameters = new HashMap<String, String>();
        }
        try {
            oauthParameters.putAll(getOAuthParamsFromURL(webRequest));
        } catch (final IOException ioe) {
            logger.error("An error occurred while resolving OAuth parameters from HTTP request", ioe);
        }
        oauthParameters.remove("realm");
        return oauthParameters;
    }

    /**
     * Gets the auth params from header.
     *
     * @param authorizationHeader the authorization header
     * @return the auth params from header
     */
    private Map<String, String> getAuthParamsFromHeader(String authorizationHeader) {
        authorizationHeader = StringUtils.removeStartIgnoreCase(authorizationHeader, "OAuth ");
        final String[] paramsList = StringUtils.split(authorizationHeader, ",");
        final Map<String, String> oauthParamMap = new HashMap<String, String>();
        if (paramsList == null) {
            return oauthParamMap;
        }
        for (final String parameter : paramsList) {
            final String[] oauthParam = StringUtils.split(parameter, "=", 2);
            if (oauthParam.length > 1) {
                try {
                    final String paramName = StringUtils.trim(oauthParam[0]);
                    if (paramName.equalsIgnoreCase("OAuth realm")) {
                        continue;
                    }
                    String paramValue = StringUtils.strip(URLDecoder.decode(StringUtils.trim(oauthParam[1]), "UTF-8"), "\"");
                    if (paramName.equals(OAUTH_SIGNATURE_PARAM) && StringUtils.isNotBlank(paramValue)) {
                        paramValue = paramValue.replace(" ", "+");
                    }
                    oauthParamMap.put(paramName, paramValue);
                } catch (final UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return oauthParamMap;
    }

    /**
     * Gets the o auth params from url.
     *
     * @param parameterMap the parameter map
     * @return the o auth params from url
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getOAuthParamsFromURL(final HttpServletRequest webRequest) throws IOException {
        @SuppressWarnings("rawtypes")
        final Map parameterMap = webRequest.getParameterMap();
        if (parameterMap == null) {
            return null;
        }
        final Map<String, String> requestParamMap = new HashMap<String, String>();
        final Set<Entry<String, Object>> entrySet = parameterMap.entrySet();
        for (final Entry<String, Object> parameterEntry : entrySet) {
            final String key = parameterEntry.getKey();
            if (key.equals("_")) {
                continue;
            }

            final Object paramValue = parameterEntry.getValue();
            String value = null;
            if (paramValue instanceof String[]) {
                value = ((String[]) paramValue)[0];
            } else {
                value = (String) paramValue;
            }
            if (key.equals(OAUTH_SIGNATURE_PARAM) && StringUtils.isNotBlank(value)) {
                value = value.replace(" ", "+");
            }
            requestParamMap.put(parameterEntry.getKey(), value);
        }
        requestParamMap.putAll(extractParametersFromStream(webRequest));
        return requestParamMap;
    }

    private Map<String, String> extractParametersFromStream(final HttpServletRequest webRequest) throws IOException {
        final Map<String, String> queryParamMap = new HashMap<String, String>();
        if (webRequest instanceof ApiRequestWrapper) {
            final BufferedReader requestStream = new BufferedReader(new InputStreamReader(webRequest.getInputStream()));
            String requestParam = null;
            try {
                requestParam = requestStream.readLine();
            } finally {
                IOUtils.closeQuietly(requestStream);
            }

            final String[] praramterList = StringUtils.split(requestParam, "&");
            if (praramterList == null) {
                return queryParamMap;
            }
            for (final String parameterText : praramterList) {
                final String[] parameter = StringUtils.split(parameterText, "=", 2);
                if (parameter != null) {
                    String value = "";
                    if (parameter.length > 1) {
                        value = URLDecoder.decode(StringUtils.defaultString(parameter[1]), "UTF-8");
                    }
                    queryParamMap.put(parameter[0], value);
                }
            }
        }
        return queryParamMap;
    }

}
