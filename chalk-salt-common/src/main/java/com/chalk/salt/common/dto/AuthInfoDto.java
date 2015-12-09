/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.common.dto;

/**
 * The Class DomainUserInfo.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class AuthInfoDto {

    /** The user detail. */
    private UserDetailDto userDetail;

    /** The ent jndi name. */
    private String officeJndi;

    /** The sys jndi name. */
    private String systemJndi;

    /** The number of licenses. */
    private int numberOfLicenses;

    /** The max allowed failure login attempts. */
    private int maxAllowedFailureLoginAttempts;
    
    /** The office database. */
    private String officeDatabase;

    /**
     * Gets the user detail.
     *
     * @return the user detail
     */
    public UserDetailDto getUserDetail() {
        return userDetail;
    }

    /**
     * Sets the user detail.
     *
     * @param userDetail the new user detail
     */
    public void setUserDetail(UserDetailDto userDetail) {
        this.userDetail = userDetail;
    }

    /**
     * Gets the number of licenses.
     *
     * @return the number of licenses
     */
    public int getNumberOfLicenses() {
        return numberOfLicenses;
    }

    /**
     * Sets the number of licenses.
     *
     * @param numberOfLicenses the new number of licenses
     */
    public void setNumberOfLicenses(final int numberOfLicenses) {
        this.numberOfLicenses = numberOfLicenses;
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
    public void setOfficeJndi(final String officeJndi) {
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
    public void setSystemJndi(final String systemJndi) {
        this.systemJndi = systemJndi;
    }

    /**
     * Gets the max allowed failure login attempts.
     *
     * @return the max allowed failure login attempts
     */
    public int getMaxAllowedFailureLoginAttempts() {
        return maxAllowedFailureLoginAttempts;
    }

    /**
     * Sets the max allowed failure login attempts.
     *
     * @param maxAllowedFailureLoginAttempts the new max allowed failure login attempts
     */
    public void setMaxAllowedFailureLoginAttempts(final int maxAllowedFailureLoginAttempts) {
        this.maxAllowedFailureLoginAttempts = maxAllowedFailureLoginAttempts;
    }

    /**
     * Gets the office database.
     *
     * @return the officeDatabase
     */
    public String getOfficeDatabase() {
        return officeDatabase;
    }

    /**
     * Sets the office database.
     *
     * @param officeDatabase the officeDatabase to set
     */
    public void setOfficeDatabase(String officeDatabase) {
        this.officeDatabase = officeDatabase;
    }

}
