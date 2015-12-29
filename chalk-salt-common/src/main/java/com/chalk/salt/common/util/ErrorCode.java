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

    /** The user session required. */
    USER_SESSION_REQUIRED(1006),

    /** The guest required. */
    GUEST_REQUIRED(1007),

    /** The authorization failure. */
    AUTHORIZATION_FAILURE(1008),

    /** The generic authentication error. */
    GENERIC_AUTHENTICATION_ERROR(1009),

    /** The parameter missing invalid. */
    PARAMETER_MISSING_INVALID(1010),

    /** The un-processable entity. */
    UNPROCESSABLE_ENTITY(1011),

    /** The resource already processed. */
    RESOURCE_ALREADY_PROCESSED(1012),

    /** The resource expired. */
    RESOURCE_EXPIRED(1013),

    /** The entity already exists. */
    ENTITY_ALREADY_EXISTS(1014),

    /** The resource not accessible. */
    RESOURCE_NOT_ACCESSIBLE(1015),

    /** The resource not modified. */
    RESOURCE_NOT_MODIFIED(1016),

    /** The malformed request format. */
    MALFORMED_REQUEST_FORMAT(1017),

    /** The unsupported media type. */
    UNSUPPORTED_MEDIA_TYPE(1018),

    /** The login count per user exceeded. */
    LOGIN_COUNT_PER_USER_EXCEEDED(1019),

    /** The user licence count exceeded. */
    USER_LICENCE_COUNT_EXCEEDED(1020),

    /** The max allowed failure login exceeded. */
    MAX_ALLOWED_FAILURE_LOGIN_EXCEEDED(1021),

    /** The incorrect credentials. */
    INCORRECT_CREDENTIALS(1022),

    /** The ip not allowed. */
    IP_NOT_ALLOWED(1023),

    /** The mandatory values not filled. */
    MANDATORY_VALUES_NOT_FILLED(1024),

    /** The user already exits. */
    USER_ALREADY_EXITS(1025),

    /** The user registration failure. */
    USER_REGISTRATION_FAILURE(1026),

    /** The fail to load user registration screen. */
    FAIL_TO_LOAD_USER_REGISTRATION_SCREEN(1027),

    /** The fail to fetch user icons. */
    FAIL_TO_FETCH_USER_ICONS(1028),

    /** The fail to save user icons. */
    FAIL_TO_SAVE_USER_ICONS(1029),

    /** The no domain assigned. */
    NO_DOMAIN_ASSIGNED(1029),

    /** The fail to send email to registered user. */
    FAIL_TO_SEND_EMAIL_TO_REGISTERED_USER(1030),

    /** The fail to fetch registered users. */
    FAIL_TO_FETCH_REGISTERD_USERS(1031),

    /** The fail to fetch locale. */
    FAIL_TO_FETCH_LOCALE(1032),

    /** The fail to disable user. */
    FAIL_TO_DISABLE_USER(1033),

    /** The fail to save user info. */
    FAIL_TO_SAVE_USER_INFO(1034),

    /** The fail to fetch workflow templates. */
    FAIL_TO_FETCH_WORKFLOW_TEMPLATES(1035),

    /** The fail to fetch kpi. */
    FAIL_TO_FETCH_KPI(1036),

    /** The fail to fetch user templates. */
    FAIL_TO_FETCH_USER_TEMPLATES(1037),

    /** The fail to fetch mail servers. */
    FAIL_TO_FETCH_MAIL_SERVERS(1041),

    /** The fail to fetch offices. */
    FAIL_TO_FETCH_OFFICES(1042),

    /** The fail to fetch group access. */
    FAIL_TO_FETCH_GROUP_ACCESS(1043),

    /** The fail to fetch access rights. */
    FAIL_TO_FETCH_ACCESS_RIGHTS(1044),

    /** The fail to fetch workflow accessname. */
    FAIL_TO_FETCH_WORKFLOW_ACCESSNAME(1045),

    /** The fail to fetch workflow acceess matrix. */
    FAIL_TO_FETCH_WORKFLOW_ACCEESS_MATRIX(1046),

    /** The fail to fetch letter templates. */
    FAIL_TO_FETCH_LETTER_TEMPLATES(1047),

    /** The fail to fetch letter accessname. */
    FAIL_TO_FETCH_LETTER_ACCESSNAME(1048),

    /** The fail to fetch letter template matrix. */
    FAIL_TO_FETCH_LETTER_TEMPLATE_MATRIX(1049),
    
    /** The fail to fetch report templates. */
    FAIL_TO_FETCH_REPORT_TEMPLATES(1050),
    
    /** The fail to fetch report access templates. */
    FAIL_TO_FETCH_REPORT_ACCESS_TEMPLATES(1051),
    
    /** The fail to fetch report access matrix. */
    FAIL_TO_FETCH_REPORT_ACCESS_MATRIX(1052),
    
    /** The fail to fetch classes. */
    FAIL_TO_FETCH_CLASSES(1053); 
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
