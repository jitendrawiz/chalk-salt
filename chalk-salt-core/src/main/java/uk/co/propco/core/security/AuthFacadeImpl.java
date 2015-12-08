package com.chalk.salt.core.security;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthInfoDto;
import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.auth.manager.AuthManager;

/**
 * The Class SecurityFacadeImpl.
 */
public class AuthFacadeImpl implements AuthFacade {

    /** The auth manager. */
    @Inject
    private AuthManager authManager;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /*
     * (non-Javadoc)
     * 
     * @see
     * uk.co.techblue.propco.enterprise.common.facade.interfaces.SecurityFacade#authenticate(uk.co.techblue.propco.enterprise
     * .common.dto.AuthRequest)
     */
    @Override
    public AuthInfoDto getUserAuthenticationDetails(final AuthRequest authRequest) throws UserException {
        logger.info("About to obtain the user authentication details against the user '{}',", authRequest.getUsername());
        return authManager.getUserAuthenticationDetails(authRequest);
    }
}
