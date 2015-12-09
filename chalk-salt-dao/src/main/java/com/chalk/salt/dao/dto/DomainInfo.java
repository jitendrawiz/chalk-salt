/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.dto;

/**
 * The Class DomainInfo.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class DomainInfo {

    /** The user id. */
    private Long userId;

    /** The iref. */
    private Long iref;

    /** The master id. */
    private String masterId;

    /** The secur uuid. */
    private String securUuid;

    /** The user type. */
    private String userType;

    /** The is default. */
    private boolean isDefault;

    /** The active. */
    private boolean active;

    /** The username. */
    private String username;

    /** The system jndi. */
    private String systemJndi;

    /** The password. */
    private String password;

    /** The salt. */
    private String salt;

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the iref.
     *
     * @return the iref
     */
    public Long getIref() {
        return iref;
    }

    /**
     * Sets the iref.
     *
     * @param iref the new iref
     */
    public void setIref(final Long iref) {
        this.iref = iref;
    }

    /**
     * Gets the master id.
     *
     * @return the master id
     */
    public String getMasterId() {
        return masterId;
    }

    /**
     * Sets the master id.
     *
     * @param masterId the new master id
     */
    public void setMasterId(final String masterId) {
        this.masterId = masterId;
    }

    /**
     * Gets the secur uuid.
     *
     * @return the secur uuid
     */
    public String getSecurUuid() {
        return securUuid;
    }

    /**
     * Sets the secur uuid.
     *
     * @param securUuid the new secur uuid
     */
    public void setSecurUuid(final String securUuid) {
        this.securUuid = securUuid;
    }

    /**
     * Gets the user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     *
     * @param userType the new user type
     */
    public void setUserType(final String userType) {
        this.userType = userType;
    }

    /**
     * Checks if is default.
     *
     * @return true, if is default
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Sets the default.
     *
     * @param isDefault the new default
     */
    public void setDefault(final boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active.
     *
     * @param active the new active
     */
    public void setActive(final boolean active) {
        this.active = active;
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
    public void setSystemJndi(final String systemJndi) {
        this.systemJndi = systemJndi;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(final String username) {
        this.username = username;
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

    /**
     * Gets the salt.
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     *
     * @param salt the new salt
     */
    public void setSalt(final String salt) {
        this.salt = salt;
    }

}
