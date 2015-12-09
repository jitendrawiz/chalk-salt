/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.resource;

import org.apache.shiro.SecurityUtils;

import com.chalk.salt.api.model.security.UserPrincipal;

/**
 * The Class AbstractResource.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public abstract class AbstractResource {

    /**
     * Gets the agent principal.
     *
     * @return the agent principal
     */
    protected UserPrincipal getUserPrincipal() {
        final Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof UserPrincipal) {
            return (UserPrincipal) principal;
        }
        return null;
    }

}