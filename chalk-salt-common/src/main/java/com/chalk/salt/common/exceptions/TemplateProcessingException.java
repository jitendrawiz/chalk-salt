package com.chalk.salt.common.exceptions;

public class TemplateProcessingException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7932916097622687572L;

    /**
     * Instantiates a new template processing exception.
     */
    public TemplateProcessingException() {
        super();
    }

    /**
     * Instantiates a new template processing exception.
     * 
     * @param message the message
     */
    public TemplateProcessingException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new template processing exception.
     * 
     * @param throwable the throwable
     */
    public TemplateProcessingException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Instantiates a new template processing exception.
     * 
     * @param message the message
     * @param throwable the throwable
     */
    public TemplateProcessingException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
