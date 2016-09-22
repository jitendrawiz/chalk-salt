package com.chalk.salt.api.model.security;

import com.chalk.salt.api.model.ApiModel;
import com.chalk.salt.common.dto.ErrorResponse;

public class DomainUserInfo extends ApiModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2209181310596683562L;

    /** The domain id. */
    private Integer domainId;

    /** The domain name. */
    private String domainName;

    /** The user id. */
    private Long userId;

    /** The user name. */
    private String userName;

    /** The password. */
    private String password;

    /** The first name. */
    private String fullName;

    /** The email. */
    private String email;

    /** The error response. */
    private ErrorResponse errorResponse;

    /** The salt. */
    private String salt;

    /** The ent jndi name. */
    private String entJndiName;

    /** The sys jndi name. */
    private String sysJndiName;

    /** The max allowed failure login attempts. */
    private Long maxAllowedFailureLoginAttempts;

    /** The secur uuid. */
    private String securUuid;

    /**
     * Gets the domain id.
     *
     * @return the domain id
     */
    public Integer getDomainId() {
        return domainId;
    }

    /**
     * Sets the domain id.
     *
     * @param domainId the new domain id
     */
    public void setDomainId(final Integer domainId) {
        this.domainId = domainId;
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

    /**
     * Gets the domain name.
     *
     * @return the domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets the domain name.
     *
     * @param domainName the new domain name
     */
    public void setDomainName(final String domainName) {
        this.domainName = domainName;
    }

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
     * Gets the error response.
     *
     * @return the error response
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    /**
     * Sets the error response.
     *
     * @param errorResponse the new error response
     */
    public void setErrorResponse(final ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
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
     * @param salt the salt to set
     */
    public void setSalt(final String salt) {
        this.salt = salt;
    }

    /**
     * Gets the ent jndi name.
     *
     * @return the entJNDIName
     */
    public String getEntJndiName() {
        return entJndiName;
    }

    /**
     * Sets the ent jndi name.
     *
     * @param entJndiName the new ent jndi name
     */
    public void setEntJndiName(final String entJndiName) {
        this.entJndiName = entJndiName;
    }

    /**
     * Gets the max allowed failure login attempts.
     *
     * @return the maxAllowedFailureLoginAttempts
     */
    public Long getMaxAllowedFailureLoginAttempts() {
        return maxAllowedFailureLoginAttempts;
    }

    /**
     * Sets the max allowed failure login attempts.
     *
     * @param maxAllowedFailureLoginAttempts the maxAllowedFailureLoginAttempts to set
     */
    public void setMaxAllowedFailureLoginAttempts(final Long maxAllowedFailureLoginAttempts) {
        this.maxAllowedFailureLoginAttempts = maxAllowedFailureLoginAttempts;
    }

    /**
     * Gets the sys jndi name.
     *
     * @return the sysJndiName
     */
    public String getSysJndiName() {
        return sysJndiName;
    }

    /**
     * Sets the sys jndi name.
     *
     * @param sysJndiName the sysJndiName to set
     */
    public void setSysJndiName(final String sysJndiName) {
        this.sysJndiName = sysJndiName;
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

}
