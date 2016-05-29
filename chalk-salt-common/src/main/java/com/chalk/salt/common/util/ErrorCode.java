/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.common.util;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 * The Enum ErrorCode.
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
	
	/** The fail to delete discussion topic and comments. */
	FAIL_TO_DELETE_DISCUSSION_TOPIC_AND_COMMENTS(1024),
	
	/** The fail to update discussion topic. */
	FAIL_TO_UPDATE_DISCUSSION_TOPIC(1025),
	
	/** The fail to fetch discussion topic count. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC_COUNT(1026),
	
	/** The fail to fetch discussion topic details. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC_DETAILS(1027),
	
	/** The fail to save discussion comment. */
	FAIL_TO_SAVE_DISCUSSION_COMMENT(1028),
	
	/** The fail to fetch discussion topic comments details. */
	FAIL_TO_FETCH_DISCUSSION_TOPIC_COMMENTS_DETAILS(1029),
	
	/** The fail to fetch subject detail. */
	FAIL_TO_FETCH_SUBJECT_DETAIL(1030),
	
	/** The fail to fetch comment detail. */
	FAIL_TO_FETCH_COMMENT_DETAIL(1031),
	
	/** The fail to update discussion comment. */
	FAIL_TO_UPDATE_DISCUSSION_COMMENT(1032),
	
	/** The fail to delete discussion comment. */
	FAIL_TO_DELETE_DISCUSSION_COMMENT(1033),
	
	/** The fail to fetch students list. */
	FAIL_TO_FETCH_STUDENTS_LIST(1034),
	
    /** The fail to delete student. */
    FAIL_TO_DELETE_STUDENT(1035),
    
    /** The enquiry sent failure. */
    ENQUIRY_SENT_FAILURE(1036),
    
    /** The fail to save topic request. */
    FAIL_TO_SAVE_TOPIC_REQUEST(1037),
    
    /** The fail to fetch topic requests. */
    FAIL_TO_FETCH_TOPIC_REQUESTS(1038),
    
    /** The fail to approve topic requests. */
    FAIL_TO_APPROVE_TOPIC_REQUESTS(1039),
    
    /** The fail to update profile photo. */
    FAIL_TO_UPDATE_PROFILE_PHOTO(1040),
    
    /** The fail to delete profile photo. */
    FAIL_TO_DELETE_PROFILE_PHOTO(1041),
    
    /** The fail to update topic image. */
    FAIL_TO_UPDATE_TOPIC_IMAGE(1042),
    
    /** The fail to fetch user image path. */
    FAIL_TO_FETCH_USER_IMAGE_PATH(1043),
    
    /** The fail to fetch topic image path. */
    FAIL_TO_FETCH_TOPIC_IMAGE_PATH(1044),
	
	/** The fail to save question. */
	FAIL_TO_SAVE_QUESTION(1045),
	
	/** The fail to update question. */
	FAIL_TO_UPDATE_QUESTION(1046),
	
	/** The fail to fetch question list. */
	FAIL_TO_FETCH_QUESTION_LIST(1047),
	
	/** The fail to delete question. */
	FAIL_TO_DELETE_QUESTION(1048),
	
	/** The fail to update question image. */
	FAIL_TO_UPDATE_QUESTION_IMAGE(1049), 
	
	/** The fail to delete question image. */
	FAIL_TO_DELETE_QUESTION_IMAGE(1050), 
	
	/** The fail to delete topic image. */
	FAIL_TO_DELETE_TOPIC_IMAGE(1051),
	
	/** The fail to fetch dashboard data. */
	FAIL_TO_FETCH_DASHBOARD_DATA(1052),
	
	/** The fail to fetch student detail. */
	FAIL_TO_FETCH_STUDENT_DETAIL(1053),
	
	/** The fail to send email for reset password. */
	FAIL_TO_SEND_EMAIL_FOR_RESET_PASSWORD(1054),
	
	/** The fail to reset password. */
	FAIL_TO_RESET_PASSWORD(1055);
	
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
