/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.api.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.exceptions.CoreException;
import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class Utility.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class Utility {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    /** The bcrypt hash iterations. */
    static Integer BCRYPT_HASH_ITERATIONS = 12;

    /**
     * Builds the error response.
     *
     * @param throwable the throwable
     * @return the error response
     */
    public static final ErrorResponse buildErrorResponse(final Throwable throwable) {
        return buildErrorResponse(ErrorCode.GENERIC_SERVER_ERROR, throwable.getMessage());
    }

    /**
     * Builds the error response.
     *
     * @param errorCode the error code
     * @param message the message
     * @return the error response
     */
    public static ErrorResponse buildErrorResponse(final ErrorCode errorCode, final String message) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setMessage(message);
        return errorResponse;
    }

    /**
     * Builds the invalid parameter response.
     *
     * @param errorMessages the error messages
     * @return the error response
     */
    public static ErrorResponse buildInvalidParameterResponse(final Map<String, String> errorMessages) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.PARAMETER_MISSING_INVALID);
        errorResponse.setMessage("Required parameter(s) are invalid or missing");
        errorResponse.setErrors(errorMessages);
        return errorResponse;
    }

    /**
     * Builds the unprocessable entity response.
     *
     * @param errorMessages the error messages
     * @return the error response
     */
    public static ErrorResponse buildUnprocessableEntityResponse(final Map<String, String> errorMessages) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.UNPROCESSABLE_ENTITY);
        errorResponse.setMessage("Submitted entity detail(s) are invalid or missing");
        errorResponse.setErrors(errorMessages);
        return errorResponse;
    }

    /**
     * Builds the unprocessable application response.
     *
     * @param errorMessages the error messages
     * @return the error response
     */
    public static ErrorResponse buildUnprocessableApplicationResponse(final Map<String, Map<String, String>> errorMessages) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.UNPROCESSABLE_ENTITY);
        errorResponse.setMessage("Submitted entity detail(s) are invalid or missing");
        errorResponse.setApplicationErrors(errorMessages);
        return errorResponse;
    }

    /**
     * Builds the resource exception.
     *
     * @param <T> the generic type
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param httpStatus the http status
     * @param exceptionClass the exception class
     * @param cause the cause of type throwable
     * @return the resource exception
     */
    public static final <T extends CoreException> T buildResourceException(final ErrorCode errorCode, final String errorMessage, final Status httpStatus,
        final Class<T> exceptionClass, final Throwable cause) {
        try {
            T exception = null;
            if (cause != null) {
                exception = exceptionClass.getConstructor(String.class, Throwable.class).newInstance(errorMessage, cause);
            } else {
                exception = exceptionClass.getConstructor(String.class).newInstance(errorMessage);
            }
            exception.setErrorResponse(Utility.buildErrorResponse(errorCode, errorMessage));
            //exception.setHttpStatus(httpStatus);
            return exception;
        } catch (final Exception exception) {
            throw new RuntimeException("An error occurred while initializing exception through reflection.", exception);
        }

    }

    /**
     * Builds the internal server error.
     *
     * @param <T> the generic type
     * @param exceptionClass the exception class
     * @param cause the cause
     * @return the t
     */
    public static final <T extends CoreException> T buildInternalServerError(final Class<T> exceptionClass, final Throwable cause) {
        return buildResourceException(ErrorCode.GENERIC_SERVER_ERROR, "An error occurred on server while processing request", Status.INTERNAL_SERVER_ERROR,
            exceptionClass, cause);
    }

    /**
     * Builds the resource exception.
     *
     * @param <T> the generic type
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param httpStatus the http status
     * @param exceptionClass the exception class
     * @return the resource exception
     */
    public static final <T extends CoreException> T buildResourceException(final ErrorCode errorCode, final String errorMessage, final Status httpStatus,
        final Class<T> exceptionClass) {
        return buildResourceException(errorCode, errorMessage, httpStatus, exceptionClass, null);
    }

    /**
     * Builds the invalid parameter exception.
     *
     * @param <T> the generic type
     * @param errorMessages the error messages
     * @param exceptionClass the exception class
     * @return the t
     */
    public static final <T extends CoreException> T buildInvalidParameterException(final Map<String, String> errorMessages, final Class<T> exceptionClass) {
        try {
            final T exception = exceptionClass.getConstructor(String.class).newInstance("Required parameter(s) are invalid or missing");
            exception.setErrorResponse(buildInvalidParameterResponse(errorMessages));
            //exception.setHttpStatus(Status.BAD_REQUEST);
            return exception;
        } catch (final Exception exception) {
            throw new RuntimeException("An error occurred while initializing exception through reflection.", exception);
        }
    }

    /**
     * Builds the unprocessable entity exception.
     *
     * @param <T> the generic type
     * @param errorMessages the error messages
     * @param exceptionClass the exception class
     * @return the t
     */
    public static final <T extends CoreException> T buildUnprocessableEntityException(final Map<String, String> errorMessages, final Class<T> exceptionClass) {
        try {
            final T exception = exceptionClass.getConstructor(String.class).newInstance("Submitted entity details are invalid or missing");
            exception.setErrorResponse(buildUnprocessableEntityResponse(errorMessages));
            exception.setHttpStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
            return exception;
        } catch (final Exception exception) {
            throw new RuntimeException("An error occurred while initializing exception through reflection.", exception);
        }
    }

    /**
     * Gets the copy of date.
     *
     * @param dateObject the date object
     * @return the copy of date
     */
    public static Date getCopyOfDate(final Date dateObject) {
        if (dateObject == null) {
            return null;
        }
        return new Date(dateObject.getTime());
    }

    /**
     * Builds the error response.
     *
     * @param errorCode the error code
     * @param message the message
     * @param errors the errors
     * @return the error response
     */
    public static ErrorResponse buildErrorResponse(final ErrorCode errorCode, final String message, final Map<String, String> errors) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setMessage(message);
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    /**
     * Parses the error message.
     *
     * @param constraintViolation the constraint violation
     * @return the string[]
     */
    public static String[] parseErrorMessage(final ConstraintViolation<?> constraintViolation) {
        final String[] errorInfo = StringUtils.split(constraintViolation.getMessage(), ":", 2);
        if (errorInfo == null || errorInfo.length < 2) {
            throw new IllegalArgumentException("Validation message should be published in format <fiename>:<errorMessage>. Found-"
                + constraintViolation.getMessage());
        }
        return errorInfo;
    }

    /**
     * Builds the validation error response.
     *
     * @param constarintViolations the constarint violations
     * @return the response
     */
    public static Response buildValidationErrorResponse(final Collection<ConstraintViolation<?>> constarintViolations) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.PARAMETER_MISSING_INVALID);
        errorResponse.setMessage("Required parameters are invalid or missing");
        for (final ConstraintViolation<?> constraintViolation : constarintViolations) {
            final String[] errorInfo = Utility.parseErrorMessage(constraintViolation);
            errorResponse.addError(errorInfo[0], errorInfo[1]);
        }
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(errorResponse).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    /**
     * Probe content mime-type.
     *
     * @param filePath the file path
     * @return the string
     */
    public static String probeContentType(final String filePath) {
        String mediaType = null;
        try {
            mediaType = Files.probeContentType(Paths.get(filePath));
        } catch (final IOException ioe) {
            LOGGER.error("An error occurred while probing media type for file at path '" + filePath + "'", ioe);
        }
        if (StringUtils.isBlank(mediaType)) {
            LOGGER.warn("Unable to probe media type for file path - '{}'. Marking default media type as 'application/octet-stream'", filePath);
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return mediaType;
    }

    /**
     * Builds the resource exception.
     *
     * @param <T> the generic type
     * @param errorCode the error code
     * @param exception the exception
     * @param httpStatus the http status
     * @param exceptionClass the exception class
     * @return the t
     */
    public static final <T extends CoreException> T buildResourceException(final ErrorCode errorCode, final Exception exception, final Status httpStatus,
        final Class<T> exceptionClass) {
        String errorMessage = exception.getMessage();
        Throwable throwable = exception.getCause();
        while (throwable != null && StringUtils.isBlank(errorMessage)) {
            errorMessage = throwable.getMessage();
            throwable = throwable.getCause();
        }
        return buildResourceException(errorCode, errorMessage, httpStatus, exceptionClass, exception);
    }

    /**
     * Gets the date from string.
     *
     * @param dateString the date string
     * @return the date from string
     */
    public static Date getDateFromString(final String dateString) {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.year(), 4).appendLiteral("-")
            .appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2).appendLiteral("-").appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2)
            .toFormatter().withZoneUTC();
        try {
            final DateTime dt = formatter.parseDateTime(dateString);
            return DateUtils.truncate(dt.toDate(), Calendar.DATE);
        } catch (final Exception pe) {
            return null;
        }
    }

    /**
     * Gets the bcrypt hash bytes.
     *
     * @param password the password
     * @param salt the salt
     * @return the bcrypt hash bytes
     */
    public static byte[] getBCryptHashBytes(final String password, final String salt) {
        return BCrypt.hashpw(password, salt).getBytes();
    }

    /**
     * Check password.
     *
     * @param password the password
     * @param hashed the hashed
     * @return true, if successful
     */
    public static boolean checkBCryptPassword(final String password, final String hashed) {
        final String decodeHashed = new String(Base64.decodeBase64(hashed));
        return BCrypt.checkpw(password, decodeHashed);
    }

    /**
     * Gets the encoded bcrypt hash.
     *
     * @param password the password
     * @return the encoded bcrypt hash
     */
    public static String getEncodedBCryptHash(final String password) {
        return Base64.encodeBase64String(getBCryptHashBytes(password, generateBCryptSalt()));
    }

    /**
     * Generate bcrypt salt.
     *
     * @return the string
     */
    public static String generateBCryptSalt() {
        SecureRandom saltGen = null;
        try {
            saltGen = SecureRandom.getInstance("SHA1PRNG");
            return BCrypt.gensalt(BCRYPT_HASH_ITERATIONS, saltGen);
        } catch (final NoSuchAlgorithmException nsae) {
            LOGGER.error("An error occurred while generating random salt characters.", nsae);
        }
        return StringUtils.EMPTY;
    }

}
