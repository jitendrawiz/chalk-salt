/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class Credential.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class CredentialModel extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1803927195478593412L;

    /** The user name. */
    @JsonProperty
    private String userName;

    /** The password. */
    @JsonProperty
    private String password;

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

}
