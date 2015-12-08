/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.exceptions.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.jboss.resteasy.spi.UnsupportedMediaTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.exceptions.CoreException;
import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class GenericExceptionMapper.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final Exception exception) {
        logger.error("A server error occurred while serving REST API request. Exception mapped to http response.", exception);
        if (exception instanceof UnsupportedMediaTypeException) {
            return Response.status(Status.UNSUPPORTED_MEDIA_TYPE).entity(Utility.buildErrorResponse(ErrorCode.UNSUPPORTED_MEDIA_TYPE, "Request body is not a valid Json format"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        } else if (exception instanceof JsonParseException || exception instanceof UnrecognizedPropertyException) {
            return Response.status(Status.BAD_REQUEST).entity(Utility.buildErrorResponse(ErrorCode.MALFORMED_REQUEST_FORMAT, "Request body is not a valid Json format"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        } else if (exception instanceof CoreException) {
            return Response.status(Status.BAD_REQUEST).entity(Utility.buildErrorResponse(((CoreException) exception).getErrorCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        } else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Utility.buildErrorResponse(ErrorCode.GENERIC_SERVER_ERROR, "An internal server error occurred"))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        }
    }

}
