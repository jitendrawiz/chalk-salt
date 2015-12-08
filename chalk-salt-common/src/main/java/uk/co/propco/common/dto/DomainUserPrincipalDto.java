/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.common.dto;

/**
 * The Class DomainUserPrincipal.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
public class DomainUserPrincipalDto{

    /** The user name. */
    private Long userId;

    /** The secur uuid. */
    private String securUuid;

    /** The user name. */
    private String userName;

    /** The first name. */
    private String fullName;

    /** The email. */
    private String email;

    /** The office jndi name. */
    private String officeJndi;

    /** The system jndi name. */
    private String systemJndi;

    /** The max allowed failure login attempts. */
    private int maxAllowedFailureLoginAttempts;

    /**
     * Instantiates a new domain user principal.
     *
     * @param userId the user id
     * @param userName the user name
     */

    public DomainUserPrincipalDto() {
        
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name.
     *
     * @param fullName the new full name
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the max allowed failure login attempts.
     *
     * @return the maxAllowedFailureLoginAttempts
     */
    public int getMaxAllowedFailureLoginAttempts() {
        return maxAllowedFailureLoginAttempts;
    }

    /**
     * Sets the max allowed failure login attempts.
     *
     * @param maxAllowedFailureLoginAttempts the maxAllowedFailureLoginAttempts to set
     */
    public void setMaxAllowedFailureLoginAttempts(final int maxAllowedFailureLoginAttempts) {
        this.maxAllowedFailureLoginAttempts = maxAllowedFailureLoginAttempts;
    }

    /**
     * Gets the user id.
     *
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the userId to set
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * Sets the user name.
     *
     * @param userName the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets the secur uuid.
     *
     * @return the securUuid
     */
    public String getSecurUuid() {
        return securUuid;
    }

    /**
     * Sets the secur uuid.
     *
     * @param securUuid the securUuid to set
     */
    public void setSecurUuid(final String securUuid) {
        this.securUuid = securUuid;
    }

    /**
     * Gets the office jndi.
     *
     * @return the office jndi
     */
    public String getOfficeJndi() {
        return officeJndi;
    }

    /**
     * Sets the office jndi.
     *
     * @param officeJndi the new office jndi
     */
    public void setOfficeJndi(String officeJndi) {
        this.officeJndi = officeJndi;
    }

    /**
     * Gets the system jndi.
     *
     * @return the system jndi
     */
    public String getSystemJndi() {
        return systemJndi;
    }

    /**
     * Sets the system jndi.
     *
     * @param systemJndi the new system jndi
     */
    public void setSystemJndi(String systemJndi) {
        this.systemJndi = systemJndi;
    }

}
