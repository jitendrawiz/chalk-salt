package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.util.ErrorCode;


public class CoreException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4459486228192238521L;

    /** The error code. */
    private ErrorCode errorCode;
    
    private int httpStatusCode;
    
    private ErrorResponse errorResponse;

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new generic exception.
     */
    public CoreException() {
        super();
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     */
    public CoreException(String message) {
        super(message);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new core exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public CoreException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new core exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public CoreException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new core exception.
     *
     * @param cause the cause
     */
    public CoreException(Throwable cause) {
        super(cause);
    }

    /**
     * @return the httpStatusCode
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * @param httpStatusCode the httpStatusCode to set
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @return the errorResponse
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    /**
     * @param errorResponse the errorResponse to set
     */
    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

}
