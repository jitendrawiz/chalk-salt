package com.chalk.salt.dao.dto;

public class SystemDetail {

    /** The iref. */
    private Long iref;

    /** The active. */
    private boolean active;

    /** The authentication type. */
    private String authenticationType;

    /** The net name. */
    private String officeJndi;

    /** The number of login attempts. */
    private int numberOfLoginAttempts;

    /** The number of license. */
    private int numberOfLicense;
    
    /** The office database. */
    private String officeDatabase;

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
     * Gets the authentication type.
     *
     * @return the authentication type
     */
    public String getAuthenticationType() {
        return authenticationType;
    }

    /**
     * Sets the authentication type.
     *
     * @param authenticationType the new authentication type
     */
    public void setAuthenticationType(final String authenticationType) {
        this.authenticationType = authenticationType;
    }

    /**
     * Gets the number of login attempts.
     *
     * @return the number of login attempts
     */
    public int getNumberOfLoginAttempts() {
        return numberOfLoginAttempts;
    }

    /**
     * Sets the number of login attempts.
     *
     * @param numberOfLoginAttempts the new number of login attempts
     */
    public void setNumberOfLoginAttempts(final int numberOfLoginAttempts) {
        this.numberOfLoginAttempts = numberOfLoginAttempts;
    }

    /**
     * Gets the number of license.
     *
     * @return the number of license
     */
    public int getNumberOfLicense() {
        return numberOfLicense;
    }

    /**
     * Sets the number of license.
     *
     * @param numberOfLicense the new number of license
     */
    public void setNumberOfLicense(final int numberOfLicense) {
        this.numberOfLicense = numberOfLicense;
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
