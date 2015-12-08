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

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.exceptions.security.ApplicationAuthorizationException;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class SecurityExceptionMapper.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
@Provider
public class SecurityExceptionMapper implements ExceptionMapper<ShiroException> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final ShiroException shiroException) {
        logger.error("An error occurred during authentication/authorisation. Exception mapped to http response in SecurityExceptionMapper.", shiroException);
        if (shiroException instanceof AuthenticationException) {

            return Response.status(Status.UNAUTHORIZED).entity(((AuthenticationException) shiroException).getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        } else if (shiroException instanceof ApplicationAuthorizationException) {

            return Response.status(Status.FORBIDDEN).entity(((ApplicationAuthorizationException) shiroException).getErrorResponse())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
        }
        return Response.status(Status.UNAUTHORIZED).entity(Utility.buildErrorResponse(ErrorCode.AUTHENTICATION_FAILURE, "Username OR password is incorrect!"))
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build();
    }

}
