package com.chalk.salt.dao.auth.manager;

import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

public interface AuthManager {

    /**
     * Authenticate.
     *
     * @param authRequest the auth request
     * @return the auth info
     * @throws UserException the generic exception
     */
    UserDto getUserAuthenticationDetails(final AuthRequest authRequest) throws UserException;

}
