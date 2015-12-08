package com.chalk.salt.core.quartz;
import org.quartz.SchedulerException;

/**
 * The Class QuartzRuntimeException.
 */
public class QuartzRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The scheduler exception. */
	private SchedulerException schedulerException;

	/**
	 * Gets the scheduler exception.
	 * 
	 * @return the scheduler exception
	 */
	public SchedulerException getSchedulerException() {
		return schedulerException;
	}

	/**
	 * Instantiates a new quartz runtime exception.
	 * 
	 * @param schedulerException
	 *            the scheduler exception
	 */
	public QuartzRuntimeException(final SchedulerException schedulerException) {
		this.schedulerException = schedulerException;
	}

	/**
	 * Instantiates a new quartz runtime exception.
	 */
	public QuartzRuntimeException() {
	}

	/**
	 * Instantiates a new quartz runtime exception.
	 * 
	 * @param message
	 *            the message
	 */
	public QuartzRuntimeException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new quartz runtime exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public QuartzRuntimeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new quartz runtime exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public QuartzRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		if (schedulerException != null) {
			return schedulerException.toString();
		}
		return super.toString();
	}
}