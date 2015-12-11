package com.chalk.salt.core.security;

import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface LoginFacade.
 */
public interface AuthFacade {

    /**
     * Authenticate.
     *
     * @param username the username
     * @param password the password
     * @param clientIpAddresses the client ip addresses
     * @return the domain user info
     * @throws UserException
     */
    UserDto getUserAuthenticationDetails(AuthRequest authDetailRequest) throws UserException;

}
