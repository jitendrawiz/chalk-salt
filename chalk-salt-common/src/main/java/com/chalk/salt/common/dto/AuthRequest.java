package com.chalk.salt.common.dto;

import java.util.List;

/**
 * The Class AuthRequest.
 */
public class AuthRequest {

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The client ip addresses. */
    private List<String> clientIpAddresses;

    /**
     * Instantiates a new auth request.
     *
     * @param username the username
     * @param password the password
     * @param clientIpAddresses the client ip addresses
     */
    public AuthRequest(final String username, final String password, final List<String> clientIpAddresses) {
        this.username = username;
        this.password = password;
        this.clientIpAddresses = clientIpAddresses;
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
     * Gets the client ip addresses.
     *
     * @return the client ip addresses
     */
    public List<String> getClientIpAddresses() {
        return clientIpAddresses;
    }

    /**
     * Sets the client ip addresses.
     *
     * @param clientIpAddresses the new client ip addresses
     */
    public void setClientIpAddresses(final List<String> clientIpAddresses) {
        this.clientIpAddresses = clientIpAddresses;
    }

}
