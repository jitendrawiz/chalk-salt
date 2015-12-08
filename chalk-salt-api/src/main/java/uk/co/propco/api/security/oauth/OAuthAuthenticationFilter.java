package com.chalk.salt.api.security.oauth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

/**
 * The Class OAuthAuthenticationFilter.
 * 
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
public class OAuthAuthenticationFilter extends AuthenticatingFilter {

    /** The Constant DEFAULT_ERROR_KEY_ATTRIBUTE_NAME. */
    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onAccessDenied(final ServletRequest request, final ServletResponse response) throws Exception {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#createToken(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse)
     */
    @Override
    protected AuthenticationToken createToken(final ServletRequest request, final ServletResponse response) {
        final String username = "demo";
        final String password = "demo";
        return createToken(username, password, request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#isRememberMe(javax.servlet.ServletRequest)
     */
    @Override
    protected boolean isRememberMe(final ServletRequest request) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#onLoginSuccess(org.apache.shiro.authc.AuthenticationToken,
     * org.apache.shiro.subject.Subject, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onLoginSuccess(final AuthenticationToken token, final Subject subject, final ServletRequest request,
            final ServletResponse response) throws Exception {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#onLoginFailure(org.apache.shiro.authc.AuthenticationToken,
     * org.apache.shiro.authc.AuthenticationException, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onLoginFailure(final AuthenticationToken token, final AuthenticationException e, final ServletRequest request,
            final ServletResponse response) {
        return true;
    }

}
