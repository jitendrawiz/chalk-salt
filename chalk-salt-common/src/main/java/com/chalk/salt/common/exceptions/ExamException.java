package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class ExamException.
 */
public class ExamException extends CoreException{

	/** The Constant serialVersionUID. */
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
    public ExamException() {
        super();
    }

    /**
     * Instantiates a new exam exception.
     *
     * @param message the message
     */
    public ExamException(String message) {
        super(message);
    }

    /**
     * Instantiates a new exam exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new exam exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public ExamException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new exam exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public ExamException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new exam exception.
     *
     * @param cause the cause
     */
    public ExamException(Throwable cause) {
        super(cause);
    }
}
