package com.chalk.salt.dao.auth.manager;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.domain.DomainDao;

public class AuthManagerImpl implements AuthManager {

    /** The domain dao. */
    @Inject
    private DomainDao domainDao;
    
    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;


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
