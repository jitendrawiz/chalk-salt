/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.exceptions.mapper;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.util.Utility;

/**
 * The Class ValidationExceptionHandler.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        logger.debug("Validation failed for one or more model data fields", exception);
        return Utility.buildValidationErrorResponse(exception.getConstraintViolations());
    }

}