package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

public class DiscussionException extends CoreException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4543875045373119468L;

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
    public DiscussionException() {
        super();
    }

    /**
     * Instantiates a new discussion exception.
     *
     * @param message the message
     */
    public DiscussionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new discussion exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public DiscussionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new discussion exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public DiscussionException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new discussion exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public DiscussionException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new discussion exception.
     *
     * @param cause the cause
     */
    public DiscussionException(Throwable cause) {
        super(cause);
    }
}
