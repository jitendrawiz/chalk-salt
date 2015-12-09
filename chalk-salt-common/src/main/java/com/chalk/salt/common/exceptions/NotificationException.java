/*
 * Copyright 2014, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.exceptions;

/**
 * The Class NotificationException.
 */
public class NotificationException extends UserException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5502644177776141546L;

    /**
     * Instantiates a new notification exception.
     */
    public NotificationException() {
        super();
    }

    /**
     * Instantiates a new notification exception.
     * 
     * @param message the message
     */
    public NotificationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new notification exception.
     * 
     * @param throwable the throwable
     */
    public NotificationException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Instantiates a new notification exception.
     * 
     * @param message the message
     * @param throwable the throwable
     */
    public NotificationException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
