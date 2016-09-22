package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

public class UserException extends CoreException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4459486228192238521L;

    /** The error code. */
    private ErrorCode errorCode;

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
    public UserException() {
        super();
    }

    /**
     * Instantiates a new user exception.
     *
     * @param message the message
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * Instantiates a new user exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new user exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public UserException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new user exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public UserException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new user exception.
     *
     * @param cause the cause
     */
    public UserException(Throwable cause) {
        super(cause);
    }

}
