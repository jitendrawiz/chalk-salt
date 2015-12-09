/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.auth.manager;

import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthInfoDto;
import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDetailDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.common.util.Utility;
import com.chalk.salt.dao.domain.DomainDao;
import com.chalk.salt.dao.dto.DomainInfo;
import com.chalk.salt.dao.dto.SystemDetail;
import com.chalk.salt.dao.system.SystemDao;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class AuthManagerImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class AuthManagerImpl implements AuthManager {

    /** The domain dao. */
    @Inject
    private DomainDao domainDao;

    /** The system dao. */
    @Inject
    private SystemDao systemDao;

    /** The office dao. */
    @Inject
    private UserDao officeDao;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /** The Constant AUTHENTICATION_TYPE. */
    private static final String AUTHENTICATION_TYPE = "IP";

    /*
     * (non-Javadoc)
     * 
     * @see
     * uk.co.techblue.propco.enterprise.common.manager.interfaces.AuthManager#authenticate(uk.co.techblue.propco.enterprise.
     * common.dto.AuthRequest)
     */
    @Override
    public AuthInfoDto getUserAuthenticationDetails(final AuthRequest authRequest) throws UserException {
        final String username = authRequest.getUsername();

        logger.info("Obtaining the user authentication details against the username '{}'", username);
        DomainInfo domainInfo = null;
        try {
            domainInfo = domainDao.obtainUserDomainDetails(username);
        } catch (Exception exception) {
        throw new UserException("Username OR password is incorrect!", exception);
        }
        if (domainInfo == null) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        final String systemJndi = domainInfo.getSystemJndi();
        final Long iRef = domainInfo.getIref();
        final String securUuid = domainInfo.getSecurUuid();
        if (StringUtils.isBlank(systemJndi) || (iRef == null || iRef <= 0) || StringUtils.isBlank(securUuid)) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        final SystemDetail systemDetail = systemDao.obtainUserSystemDetails(systemJndi, iRef);
        if (systemDetail == null) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        final List<String> clientIpAddresses = authRequest.getClientIpAddresses();
        String commaSeperatedHostAddress = null;
        try {
            commaSeperatedHostAddress = Utility.getCommaSeperatedHostName(clientIpAddresses);
        } catch (final UnknownHostException unknownHostException) {
            throw new UserException(ErrorCode.IP_NOT_ALLOWED, "Your IP Address does not match our records.Please Contact administrator.");
        }

        final boolean isUserIpAllowed = systemDao.isIPAddressAllowed(iRef, commaSeperatedHostAddress, systemJndi);
        if (AUTHENTICATION_TYPE.equals(systemDetail.getAuthenticationType()) && !isUserIpAllowed) {
            throw new UserException(ErrorCode.IP_NOT_ALLOWED, "Your IP Address does not match our records.Please Contact administrator.");
        }

        final String officeJndi = systemDetail.getOfficeJndi();
        final String officeDatabase = systemDetail.getOfficeDatabase();
        UserDetailDto userDetail;
        try {
            userDetail = officeDao.fetchUserDetails(securUuid, officeJndi);
        } catch (Exception exception) {
            throw new UserException(exception);
        }

        userDetail.setUsername(domainInfo.getUsername());
        userDetail.setPassword(domainInfo.getPassword());
        userDetail.setUserId(domainInfo.getUserId());
        final AuthInfoDto authInfo = new AuthInfoDto();
        authInfo.setOfficeJndi(officeJndi);
        authInfo.setSystemJndi(systemJndi);
        authInfo.setUserDetail(userDetail);
        authInfo.setOfficeDatabase(officeDatabase);
        authInfo.setMaxAllowedFailureLoginAttempts(systemDetail.getNumberOfLoginAttempts());
        authInfo.setNumberOfLicenses(systemDetail.getNumberOfLicense());
        return authInfo;
    }
}
