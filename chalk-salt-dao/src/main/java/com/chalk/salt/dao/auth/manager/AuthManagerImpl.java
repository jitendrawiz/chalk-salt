/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.auth.manager;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.domain.DomainDao;

/**
 * The Class AuthManagerImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class AuthManagerImpl implements AuthManager {

    /** The domain dao. */
    @Inject
    private DomainDao domainDao;
    
    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /*
     * (non-Javadoc)
     * 
     * @see
     * uk.co.techblue.propco.enterprise.common.manager.interfaces.AuthManager#authenticate(uk.co.techblue.propco.enterprise.
     * common.dto.AuthRequest)
     */
    @Override
    public UserDto getUserAuthenticationDetails(final AuthRequest authRequest) throws UserException {
        final String username = authRequest.getUsername();

        logger.info("Obtaining the user authentication details against the username '{}'", username);
        UserDto userInfo = null;
        try {
            userInfo = domainDao.obtainUserLoginDetails(username);
        } catch (Exception exception) {
        throw new UserException("Username OR password is incorrect!", exception);
        }
        if (userInfo == null) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        
        return userInfo;
    }
}
