/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.util;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * The Enum ErrorCode.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public enum ErrorCode {

    /** The none. */
    NONE(1000),

    /** The generic server error. */
    GENERIC_SERVER_ERROR(1001),

    /** The resource not found. */
    RESOURCE_NOT_FOUND(1002),

    /** The malformed auth request. */
    MALFORMED_AUTH_REQUEST(1003),

    /** The authentication failure. */
    AUTHENTICATION_FAILURE(1004),

    /** The authentication required. */
    AUTHENTICATION_REQUIRED(1005),

    /** The authorization failure. */
    AUTHORIZATION_FAILURE(1006),

    /** The generic authentication error. */
    GENERIC_AUTHENTICATION_ERROR(1007),

    /** The parameter missing invalid. */
    PARAMETER_MISSING_INVALID(1008),

    /** The un-processable entity. */
    UNPROCESSABLE_ENTITY(1009),
    
    /** The malformed request format. */
    MALFORMED_REQUEST_FORMAT(1010),

    /** The unsupported media type. */
    UNSUPPORTED_MEDIA_TYPE(1011),

    /** The incorrect credentials. */
    INCORRECT_CREDENTIALS(1012),

    /** The user already exits. */
    USER_ALREADY_EXITS(1013),

    /** The user registration failure. */
    USER_REGISTRATION_FAILURE(1014),

    /** The fail to send email to registered user. */
    FAIL_TO_SEND_EMAIL_TO_REGISTERED_USER(1015),

    /** The fail to fetch registered users. */
	FAIL_TO_FETCH_REGISTERD_USERS(1016),

    /** The fail to save user info. */
	FAIL_TO_SAVE_USER_INFO(1017),
    
    /** The fail to fetch classes. */
    FAIL_TO_FETCH_CLASSES(1018),
    
    /** The fail to update user info. */
    FAIL_TO_UPDATE_USER_INFO(1019),
	
	/** The fail to save discussion topic. */
	FAIL_TO_SAVE_DISCUSSION_TOPIC(1020),
	
	/** The fail to fetch subjects. */
	FAIL_TO_FETCH_SUBJECTS(1021),
	
	/** The fail to fetch discussion topics. */
	FAIL_TO_FETCH_DISCUSSION_TOPICS(1022),
	
	/** The fail to fetch discussion topic. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC(1023),
	
	/** The fail to delete discussion topic. */
	FAIL_TO_DELETE_DISCUSSION_TOPIC(1024),
	
	/** The fail to update discussion topic. */
	FAIL_TO_UPDATE_DISCUSSION_TOPIC(1025),
	
	/** The fail to fetch discussion topic count. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC_COUNT(1026),
	
	/** The fail to fetch discussion topic details. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC_DETAILS(1027);

	/** The value. */
    private final int value;

    /**
     * Instantiates a new error code.
     *
     * @param value the value
     */
    ErrorCode(final int value) {
        this.value = value;
    }

    /**
     * Value.
     *
     * @return the int
     */
    @JsonValue
    public int value() {
        return value;
    }

    /**
     * From value.
     *
     * @param typeCode the type code
     * @return the error code
     */
    @JsonCreator
    public static ErrorCode fromValue(final int typeCode) {
        for (final ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.value == typeCode) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("Invalid Status type code: " + typeCode);

    }

}
