package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemException.
 */
public class SystemException extends CoreException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -500733341939567237L;

	/** The error code. */
	private ErrorCode errorCode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chalk.salt.common.exceptions.CoreException#getErrorCode()
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chalk.salt.common.exceptions.CoreException#setErrorCode(com.chalk
	 * .salt.common.util.ErrorCode)
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Instantiates a new system exception.
	 */
	public SystemException() {
		super();
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message
	 *            the message
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public SystemException(final ErrorCode errorCode, final String message,
			final Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param message
	 *            the message
	 */
	public SystemException(final ErrorCode errorCode, final String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

}
