/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.model.security;

import com.chalk.salt.api.model.ApiModel;

/**
 * The Class UserPrincipal.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class UserPrincipal extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 293876432218629948L;

    /** The user id. */
    private final Long userId;

    /**
     * Instantiates a new user principal.
     *
     * @param userId the user id
     */
    public UserPrincipal(final Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

}
