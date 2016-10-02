package com.chalk.salt.common.exceptions;

import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class StudentAchievementException.
 */
public class StudentAchievementException extends CoreException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4066427282560708970L;
    /** The error code. */
    private ErrorCode errorCode;

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.common.exceptions.CoreException#getErrorCode()
     */
    public ErrorCode getErrorCode()
        {
            return errorCode;
        }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.chalk.salt.common.exceptions.CoreException#setErrorCode(com.chalk
     * .salt.common.util.ErrorCode)
     */
    public void setErrorCode(ErrorCode errorCode)
        {
            this.errorCode = errorCode;
        }

    /**
     * Instantiates a new student achievement exception.
     */
    public StudentAchievementException()
        {
            super();
        }

    /**
     * Instantiates a new student achievement exception.
     *
     * @param message
     *            the message
     */
    public StudentAchievementException(String message)
        {
            super(message);
        }

    /**
     * Instantiates a new student achievement exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public StudentAchievementException(String message, Throwable cause)
        {
            super(message, cause);
        }

    /**
     * Instantiates a new student achievement exception.
     *
     * @param errorCode
     *            the error code
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public StudentAchievementException(final ErrorCode errorCode, final String message, final Throwable cause)
        {
            super(message, cause);
            this.errorCode = errorCode;
        }

    /**
     * Instantiates a new student achievement exception.
     *
     * @param errorCode
     *            the error code
     * @param message
     *            the message
     */
    public StudentAchievementException(final ErrorCode errorCode, final String message)
        {
            super(message);
            this.errorCode = errorCode;
        }

    /**
     * Instantiates a new student achievement exception.
     *
     * @param cause
     *            the cause
     */
    public StudentAchievementException(Throwable cause)
        {
            super(cause);
        }

}
