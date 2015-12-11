/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class UserManagerImpl.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserManagerImpl implements UserManager {
   
    /** The office dao. */
    @Inject
    private UserDao officeDao;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String userName) throws UserException {
        logger.info("Checking user existance......");
        try {
            return officeDao.isUserExist(userName);
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.USER_ALREADY_EXITS, "user_already_exist", exception);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#registerUser(uk.co.propco.common.dto.User)
     */
    @Override
    public String registerUser(final UserDto userDetail) throws UserException {
        logger.info("Registering new user ......");
        try {
	        String userName = userDetail.getUserName();
	        String hashedPassword = userDetail.getPassword();
	  		String securUuid = UUID.randomUUID().toString();
			Long userId = officeDao.saveLoginDetails(userName, hashedPassword);
			userDetail.setUserId(userId);
			userDetail.setSecurUuid(securUuid);
			
			if(userId==0 || userId == null){
				throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "Fail to save User Registration");
			}
			
			Long contactId = officeDao.saveContactDetails(userDetail);
			userDetail.setContactId(contactId);
			if(!officeDao.saveUserDetails(userDetail)){
				throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "Fail to save User Registration");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        return userDetail.getSecurUuid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#fetchUsers(java.lang.String)
     */
    @Override
    public List<UserDto> fetchUsers() throws UserException {
        logger.info("Obtaining the list of users");
        /*final List<UserDto> users = new ArrayList<UserDto>();
        try {
            final Integer iref = officeDao.fetchIrefByOfficeJndi(officeJndi, systemJndi);
            if (iref == null) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail_to_fetch_registered_user");
            }
            final List<String> allowedUserUuids = officeDao.fetchAllowedUsers(iref);
            for (final String securUuid : allowedUserUuids) {
                final UserDto user = officeDao.fetchUsers(securUuid, officeJndi);
                if (user != null) {
                    users.add(user);
                }
            }
            if (CollectionUtils.isEmpty(users)) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail_to_fetch_registered_user");
            }
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail_to_fetch_registered_user", exception);
        }
        return users;*/
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#disableUser(java.lang.String, java.lang.String)
     */
    @Override
    public void disableUser(final String securUuid, final String disableDate) throws UserException {
        logger.info("Disabling user : {} from {}", securUuid, disableDate);
        try {
            final Long userId = officeDao.getUserIdBySecurUuid(securUuid);
            if (userId == null) {
                throw new UserException(ErrorCode.FAIL_TO_DISABLE_USER, "fail_to_disable_user");
            }
            officeDao.disableUser(userId, disableDate);
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_DISABLE_USER, "fail_to_disable_user", exception);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws UserException {
        logger.info("Obtaining user details for securuuid : {}", securUuid);
        UserDto user = null;
        try {
            user = officeDao.getUserInfo(securUuid);
            if (user == null) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail_to_fetch_registered_user");
            }
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail_to_fetch_registered_user", exception);
        }
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.manager.UserManager#saveUserInfo(uk.co.propco.common.dto.UserDto,
     * uk.co.propco.common.dto.DomainUserPrincipalDto)
     */
    @Override
    public String saveUserInfo(final UserDto userDetails) throws UserException {
        logger.info("Saving user details of username : {}", userDetails.getUserName());
        String securUuid = null;
        if (isUserExist(userDetails.getUserName())) {
            throw new UserException(ErrorCode.USER_ALREADY_EXITS, "user_already_exist");
        }
        try {
            securUuid = registerUser(userDetails);

        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "fail_to_save_user_info", exception);
        }
        return securUuid;
    }

}
