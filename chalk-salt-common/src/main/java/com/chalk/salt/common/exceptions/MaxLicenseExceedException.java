/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;


/**
 * The Class MaxLicenseExceedException.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class MaxLicenseExceedException extends UserException{
        
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5652275749788472574L;

    /**
     * Instantiates a new generic exception.
     */
    public MaxLicenseExceedException() {
        super();
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     */
    public MaxLicenseExceedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public MaxLicenseExceedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public MaxLicenseExceedException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(errorCode, message, cause);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public MaxLicenseExceedException(final ErrorCode errorCode, final String message) {
        super(errorCode, message);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param cause the cause
     */
    public MaxLicenseExceedException(Throwable cause) {
        super(cause);
    }
}
