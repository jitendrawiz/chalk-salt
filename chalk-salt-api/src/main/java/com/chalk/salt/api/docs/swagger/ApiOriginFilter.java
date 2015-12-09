package com.chalk.salt.api.docs.swagger;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.chalk.salt.api.util.ApiRequestWrapper;

/**
 * The Class ApiOriginFilter.
 */
public class ApiOriginFilter implements javax.servlet.Filter {

    /** The Constant SERVLET_REQUESTS. */
    private static final ThreadLocal<HttpServletRequest> SERVLET_REQUESTS = new ThreadLocal<HttpServletRequest>();

    /**
     * Gets the current http request.
     *
     * @return the current http request
     */
    public static final HttpServletRequest getCurrentHttpRequest() {
        return SERVLET_REQUESTS.get();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     * javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest webRequest = (HttpServletRequest) request;
        if (MediaType.APPLICATION_FORM_URLENCODED.equals(request.getContentType()) && "PUT".equalsIgnoreCase(webRequest.getMethod())) {
            webRequest = new ApiRequestWrapper(webRequest);
        }
        SERVLET_REQUESTS.set(webRequest);
        chain.doFilter(webRequest, response);
        SERVLET_REQUESTS.remove();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }
}
