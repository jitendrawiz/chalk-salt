package com.chalk.salt.api.model;

import java.util.List;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * The Class DomainAuthToken.
 *
 */
public class DomainAuthTokenModel extends UsernamePasswordToken {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6491711434390563739L;

    /** The client ip addresses. */
    private List<String> clientIpAddresses;

    /**
     * Instantiates a new domain auth token.
     *
     * @param username the username
     * @param password the password
     * @param rememberMe 
     * @param clientIpAddresses the client ip addresses
     */
    public DomainAuthTokenModel(final String username, final String password, Boolean rememberMe, final List<String> clientIpAddresses) {
        super(username, password, rememberMe);
        this.clientIpAddresses = clientIpAddresses;
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
