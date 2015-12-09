/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.user.manager;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.common.util.Utility;
import com.chalk.salt.dao.domain.DomainDao;
import com.chalk.salt.dao.dto.DomainInfo;
import com.chalk.salt.dao.dto.SystemDetail;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;
import com.chalk.salt.dao.system.SystemDao;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class UserManagerImpl.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserManagerImpl implements UserManager {

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

    /** The Constant STATUS_REJECTED. */
    private static final String STATUS_REJECTED = "Rejected";
    /*
     * (non-Javadoc)
     * 
     * @see
     * uk.co.techblue.propco.enterprise.common.manager.interfaces.UserManager#saveLoginStatus(uk.co.techblue.propco.enterprise
     * .common.dto.SaveLoginRequest)
     */
    @Override
    public boolean saveLoginStatus(final SaveLoginRequestDto saveLoginRequest) throws UserException {
        final AuthStatus userAuthStatus = saveLoginRequest.getStatus();
        final Long userId = saveLoginRequest.getUserId();
        final String username = saveLoginRequest.getUsername();
        final String officeJndi = saveLoginRequest.getOfficeJndi();
        final List<String> ipAddresses = saveLoginRequest.getIpAddresses();
        String commaSeperatedIpAddresses = null;
        try {
            commaSeperatedIpAddresses = Utility.getCommaSeperatedHostName(ipAddresses);
        } catch (final UnknownHostException unknownHostException) {
            throw new UserException(unknownHostException.getMessage(), unknownHostException.getCause());
        }
        
        
        try{
            final boolean userSavedSuccessfully = officeDao.saveUserLoginStatus(userId, username, commaSeperatedIpAddresses, officeJndi,
                userAuthStatus.getDescription()); 
            
            boolean lockStatus = false;
            if (STATUS_REJECTED.equals(userAuthStatus.getDescription())) {
                lockStatus = checkLoginAttempts(saveLoginRequest, officeJndi);
            }
            
            if (userSavedSuccessfully && lockStatus) {
                return true;
            }
        }
        catch(Exception exception){
            throw new UserException(exception);
        }
        return false;
    }
    
    

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#lockUser(com.chalk.salt.common.dto.SaveLoginRequestDto)
     */
    @Override
    public boolean checkLoginAttempts(SaveLoginRequestDto saveLoginRequest, String officeJndi)throws UserException {       
            try {
                logger.debug("Check User Failure Attempts exceeds allowed Login Attempts - started");

                final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                final Date currentDate = new Date();
                final Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                cal.add(Calendar.MINUTE, -30);
                
                final Date afterAddingThirtyMins = cal.getTime();
                
                int failedLoginAttempts = officeDao.getFailedLoginAttempts(officeJndi, saveLoginRequest, dateFormat.format(currentDate), dateFormat.format(afterAddingThirtyMins));                              
                
                if(failedLoginAttempts >= saveLoginRequest.getMaxLoginAttempts()){
                    officeDao.lockUser(officeJndi, saveLoginRequest,  dateFormat.format(currentDate));
                    return true;
                }               
            } catch (final Exception sqlExc) {
                throw new UserException(ErrorCode.AUTHENTICATION_FAILURE,"User Authentication Failed");
            }
            return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.manager.UserManager#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String username) throws UserException {
        try {
            return officeDao.isUserExist(username);
        } catch (Exception exception) {
            throw new UserException(exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.manager.UserManager#registerUser(com.chalk.salt.common.dto.User)
     */
    @Override
    public String registerUser(final DomainUserPrincipalDto domainDto, final UserDto userDetail) throws UserException {
        final String username = userDetail.getUsername();
        final String systemJndi = domainDto.getSystemJndi();
        List<DomainDetailDto> domains = userDetail.getDomainDetails();
        try {
            final Sql2o sql2o = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
            try(Connection conn = sql2o.beginTransaction()){
                final Long masterId = officeDao.getMasterIdBySystemJndi(systemJndi);
                final Long userId = officeDao.saveLoginDetails(username, userDetail.getPassword());
                final String securUuid = UUID.randomUUID().toString();
                userDetail.setSecurUuid(securUuid);
                List<Row> systemData = null;
                for (final DomainDetailDto domain : domains) {
                    systemData = officeDao.fetchSystemDataByIrefUuid(systemJndi, domain.getIrefUuid());
                    for (Row row : systemData) {
                        final Integer iref = row.getInteger("iref");
                        if (iref.equals(null)) {
                            throw new UserException(ErrorCode.NO_DOMAIN_ASSIGNED, "No Domain assigned to the New User");
                        }
                        boolean defaultDomain = false;
                        if (domain.isDefault()) {
                            defaultDomain = true;
                        }
                        officeDao.saveDomainDetail(userId, securUuid, masterId, iref, defaultDomain);
                    }
                }
                for (final Row row : systemData) {
                    final String netname = row.getString("netname");
                    boolean userSavedSuccessfully = officeDao.saveUserDetails(userDetail, netname);
                    if (!userSavedSuccessfully) {
                        throw new UserException(ErrorCode.USER_REGISTRATION_FAILURE, "User Registration Failed!");
                    }
                }
                conn.commit();
            }
            
        } catch (Exception exception) {
            throw new UserException("User Registration Failed!", exception);
        }
        return userDetail.getSecurUuid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.manager.UserManager#getDomainList(java.lang.String, java.lang.String)
     */
    @Override
    public List<DomainDetailDto> fetchDomains(final String securUuid, final String systemJndi) throws UserException {
        List<DomainDetailDto> domainDetail = null;
        try {
            final List<Integer> domainData = officeDao.fetchIrefBySystemJndi(securUuid, systemJndi);
            if (CollectionUtils.isEmpty(domainData)) {
                throw new UserException(ErrorCode.FAIL_TO_LOAD_USER_REGISTRATION_SCREEN, "Fail to load user registration screen!");
            }
            List<Integer> iref = new ArrayList<Integer>();
            for (final Integer domain : domainData) {
                final Integer domainIref = domain;
                iref.add(domainIref);
            }
            domainDetail = officeDao.fetchDomainDetailByIref(systemJndi, iref);
            if (CollectionUtils.isEmpty(domainDetail)) {
                throw new UserException(ErrorCode.NO_DOMAIN_ASSIGNED, "No Domain assigned to current User!");
            }
        } catch (final Exception exception) {
            throw new UserException("No Domain assigned to current User", exception);
        }
        return domainDetail;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.manager.UserManager#fetchUserIconList(java.lang.String, java.lang.String)
     */
    @Override
    public List<UserIconDto> fetchIcons(final String securUuid, final String officeJndi) throws UserException {
        try {
            return officeDao.fetchIcons(securUuid, officeJndi);
        } catch (Exception exception) {
            throw new UserException(exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.manager.UserManager#saveUserHomeIcons(com.chalk.salt.common.dto.UserIconDto)
     */
    @Override
    public boolean saveIcons(final String securId, final String officeJndi, final List<UserIconDto> userIcon)
        throws UserException {
        final Long userId;
        try {
            userId = officeDao.getUserIdBySecurUuid(securId);
           
            for (UserIconDto icon : userIcon) {
                boolean iconSavedSuccessfully = officeDao.saveIcons(userId, officeJndi, icon);
                if (!iconSavedSuccessfully) {
                    throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_ICONS, "Fail to save user configured icons!");
                }
            }
        } catch (Exception exception) {
            throw new UserException(exception);
        }
        return true;
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#saveRejectedUser(java.lang.String, java.util.List, com.chalk.salt.common.dto.AuthStatus)
     */
    @Override
    public boolean saveRejectedUserDetails(final String username, final List<String> ipAddresses, final AuthStatus status) throws UserException {
        logger.info("Obtaining the user authentication details against the username '{}'", username);
        DomainInfo domainInfo = null;
        try {
            domainInfo = domainDao.obtainUserDomainDetails(username);
        } catch (Exception exception) {
        throw new UserException("Username OR password is incorrect!", exception);
        }
        if (domainInfo.equals(null)) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        final String systemJndi = null;
        final Long iRef = null;
        final String securUuid = domainInfo.getSecurUuid();
        if (StringUtils.isBlank(systemJndi) || (iRef == null || iRef <= 0) || StringUtils.isBlank(securUuid)) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        final SystemDetail systemDetail = systemDao.obtainUserSystemDetails(systemJndi, iRef);
        if (systemDetail.equals(null)) {
            throw new UserException(ErrorCode.INCORRECT_CREDENTIALS, "Username OR password is incorrect!");
        }
        
        Long userId = domainInfo.getUserId();
        String officeJndi = systemDetail.getOfficeJndi();
        SaveLoginRequestDto saveLoginRequest = new SaveLoginRequestDto(userId, username, ipAddresses, officeJndi, systemDetail.getNumberOfLoginAttempts(), status);
        return saveLoginStatus(saveLoginRequest);
    }


    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#fetchUsers(java.lang.String)
     */
    @Override
    public List<UserDto> fetchUsers(final String officeJndi, final String systemJndi) throws UserException {
        logger.info("Obtaining the list of users");
        final List<UserDto> users = new ArrayList<UserDto>();
        try {
            Integer iref = officeDao.fetchIrefByOfficeJndi(officeJndi, systemJndi);
            if(iref.equals(null)){
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "Fail to fetch registered users!");
            }
            List<String> allowedUserUuids = officeDao.fetchAllowedUsers(iref); 
            for(String securUuid : allowedUserUuids){
                UserDto user = officeDao.fetchUsers(securUuid, officeJndi);
                users.add(user);                
            }             
            if (CollectionUtils.isEmpty(users)) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "Fail to fetch registered users!");
            }            
        } catch (final Exception exception) {
            throw new UserException("Fail to fetch registered users", exception);
        }
        return users;        
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#disableUser(java.lang.String, java.lang.String)
     */
    @Override
    public void disableUser(String securUuid, String disableDate) throws UserException {
        logger.info("Disabling User : {} from {}", securUuid, disableDate);
        try {
            Long userId = officeDao.getUserIdBySecurUuid(securUuid);
            if(userId.equals(null)){
                throw new UserException("Fail to fetch User Id by Secur Id");
            }
            officeDao.disableUser(userId, disableDate);                      
        } catch (final Exception exception) {
            throw new UserException("Fail to fetch registered users", exception);
        }
        
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(String securUuid, String officeJndi) throws UserException {
        logger.info("Obtaining User Details for Secur Uuid : {}", securUuid);
        UserDto user = null;
        try {
                user = officeDao.getUserInfo(securUuid, officeJndi);
            if (user.equals(null)) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "Fail to fetch registered users!");
            }            
        } catch (final Exception exception) {
            throw new UserException("Fail to fetch registered users", exception);
        }
        return user;  
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#saveUserInfo(com.chalk.salt.common.dto.UserDto, com.chalk.salt.common.dto.DomainUserPrincipalDto)
     */
    @Override
    public String saveUserInfo(final UserDto userDetails, final DomainUserPrincipalDto domainDto) throws UserException {
        logger.info("Saving User Details of username : {}",userDetails.getUsername());
        String securUuid = null;        
        final String officeJndi = domainDto.getOfficeJndi();
        try {
            if(!isUserExist(userDetails.getUsername())){
                securUuid = registerUser(domainDto, userDetails);
                if(!securUuid.equals(null)){
                    officeDao.getUserInfo(securUuid, officeJndi);
                }
            }
                     
        } catch (final Exception exception) {
            throw new UserException("Fail to fetch registered users", exception);
        }
        return securUuid;        
    }
    
}
