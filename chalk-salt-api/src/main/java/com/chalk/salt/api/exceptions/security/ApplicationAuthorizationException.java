package com.chalk.salt.api.exceptions.security;

import org.apache.shiro.authz.AuthorizationException;

import com.chalk.salt.common.dto.ErrorResponse;

public class ApplicationAuthorizationException extends AuthorizationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -265736087945250784L;

    /** The error response. */
    private ErrorResponse errorResponse;

    /**
     * Instantiates a new application authorization exception.
     */
    public ApplicationAuthorizationException() {
    }

    /**
     * Instantiates a new application authorization exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ApplicationAuthorizationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new application authorization exception.
     *
     * @param message the message
     */
    public ApplicationAuthorizationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new application authorization exception.
     *
     * @param cause the cause
     */
    public ApplicationAuthorizationException(final Throwable cause) {
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
