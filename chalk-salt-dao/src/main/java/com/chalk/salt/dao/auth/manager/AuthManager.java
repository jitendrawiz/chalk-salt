/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.auth.manager;

import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Class AuthManager.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
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
