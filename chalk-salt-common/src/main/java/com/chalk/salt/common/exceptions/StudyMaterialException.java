package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class StudyMaterialException.
 */
public class StudyMaterialException extends CoreException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2853914682814604927L;
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
     * Instantiates a new study material exception.
     */
    public StudyMaterialException() {
        super();
    }

    /**
     * Instantiates a new study material exception.
     *
     * @param message the message
     */
    public StudyMaterialException(String message) {
        super(message);
    }

    /**
     * Instantiates a new study material exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public StudyMaterialException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new study material exception.
     *
     * @param errorCode the error code
     * @param message the message
     * @param cause the cause
     */
    public StudyMaterialException(final ErrorCode errorCode, final String message,
        final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * Instantiates a new study material exception.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public StudyMaterialException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new study material exception.
     *
     * @param cause the cause
     */
    public StudyMaterialException(Throwable cause) {
        super(cause);
    }

    
}
