/***************************************************************************
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 **************************************************************************/
package com.chalk.salt.common.dto;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class ErrorResponse.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class ErrorResponse extends BaseDto {
    
    /** The error code. */
    @JsonProperty
    private ErrorCode errorCode;

    /** The message. */
    @JsonProperty
    private String message;

    /** The errors. */
    @JsonProperty
    private Map<String, String> errors;

    /** The application errors. */
    @JsonProperty
    private Map<String, Map<String, String>> applicationErrors;

    /**
     * Instantiates a new error response.
     */
    public ErrorResponse() {
    }

    /**
     * Instantiates a new error response.
     *
     * @param errorCode the error code
     * @param message the message
     */
    public ErrorResponse(final ErrorCode errorCode, final String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

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
    public void setErrorCode(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets the errors.
     *
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Sets the errors.
     *
     * @param errors the new errors
     */
    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * Gets the application errors.
     *
     * @return the application errors
     */
    public Map<String, Map<String, String>> getApplicationErrors() {
        return applicationErrors;
    }

    /**
     * Sets the application errors.
     *
     * @param applicationErrors the application errors
     */
    public void setApplicationErrors(final Map<String, Map<String, String>> applicationErrors) {
        this.applicationErrors = applicationErrors;
    }

    /**
     * Adds the error.
     *
     * @param fieldName the field name
     * @param errorMessage the error message
     */
    public void addError(final String fieldName, final String errorMessage) {
        if (errors == null) {
            errors = new HashMap<String, String>();
        }
        errors.put(fieldName, errorMessage);
    }
}
