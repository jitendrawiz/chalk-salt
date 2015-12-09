/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.exceptions.security;

import org.apache.shiro.authc.AuthenticationException;

import com.chalk.salt.common.dto.ErrorResponse;

/**
 * The Class ApplicationAuthenticationException.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class ApplicationAuthenticationException extends AuthenticationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2094039852399614304L;

    /** The error response. */
    private ErrorResponse errorResponse;

    /**
     * Instantiates a new application authentication exception.
     */
    public ApplicationAuthenticationException() {
    }

    /**
     * Instantiates a new application authentication exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ApplicationAuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new application authentication exception.
     *
     * @param message the message
     */
    public ApplicationAuthenticationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new application authentication exception.
     *
     * @param cause the cause
     */
    public ApplicationAuthenticationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Gets the error response.
     *
     * @return the error response
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    /**
     * Sets the error response.
     *
     * @param errorResponse the new error response
     */
    public void setErrorResponse(final ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
