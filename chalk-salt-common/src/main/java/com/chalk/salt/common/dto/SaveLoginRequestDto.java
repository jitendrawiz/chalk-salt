package com.chalk.salt.common.dto;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.chalk.salt.common.exceptions.UserException;

public class SaveLoginRequestDto {

    /** The user id. */
    private Long userId;

    /** The username. */
    private String username;

    /** The ip addresses. */
    private List<String> ipAddresses;

    /** The office jndi. */
    private String officeJndi;

    /** The max login attempts. */
    private int maxLoginAttempts;

    /** The status. */
    private AuthStatus status;

    /**
     * Instantiates a new save login request.
     *
     * @param userId the user id
     * @param username the username
     * @param ipAddresses the ip addresses
     * @param officeJndi the office jndi
     * @param maxLoginAttempts the max login attempts
     * @param status the status
     * @throws UserException the core exception
     */
    public SaveLoginRequestDto(final Long userId, final String username, final List<String> ipAddresses, final String officeJndi, final int maxLoginAttempts,
        final AuthStatus status) throws UserException {
        if (userId == null || userId == 0) {
            throw new UserException("userId can not be null / blank");
        }
        if (StringUtils.isBlank(username)) {
            throw new UserException("username can not be null / blank");
        }
        if (CollectionUtils.isEmpty(ipAddresses)) {
            throw new UserException("Invalid ip addresses");
        }
        if (status == null) {
            throw new UserException("Invalid authentication status");
        }
        if (StringUtils.isBlank(officeJndi)) {
            throw new UserException("office jndi can not be null / blank");
        }

        this.userId = userId;
        this.username = username;
        this.ipAddresses = ipAddresses;
        this.officeJndi = officeJndi;
        this.maxLoginAttempts = maxLoginAttempts;
        this.status = status;
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
     * Gets the ip addresses.
     *
     * @return the ip addresses
     */
    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    /**
     * Sets the ip addresses.
     *
     * @param ipAddresses the new ip addresses
     */
    public void setIpAddresses(final List<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
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
     * Gets the max login attempts.
     *
     * @return the max login attempts
     */
    public int getMaxLoginAttempts() {
        return maxLoginAttempts;
    }

    /**
     * Sets the max login attempts.
     *
     * @param maxLoginAttempts the new max login attempts
     */
    public void setMaxLoginAttempts(final int maxLoginAttempts) {
        this.maxLoginAttempts = maxLoginAttempts;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public AuthStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(final AuthStatus status) {
        this.status = status;
    }

}
