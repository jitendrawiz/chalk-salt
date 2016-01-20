/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class UserManagerImpl.
 */
public class UserManagerImpl implements UserManager {
   
    /** The office dao. */
    @Inject
    private UserDao userDao;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /**
     * Checks if is user exist.
     *
     * @param userName the user name
     * @return true, if is user exist
     * @throws UserException the user exception
     */
    private boolean isUserExist(final String userName) throws UserException {
        logger.info("Checking user existance......");
        try {
            return userDao.isUserExist(userName);
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.USER_ALREADY_EXITS, "user_already_exist", exception);
        }
    }

    /**
     * Register user.
     *
     * @param userDetail the user detail
     * @return the string
     * @throws UserException the user exception
     */
    private String registerUser(final UserDto userDetail) throws UserException {
        logger.info("Registering new user ......");
        try {
	        String userName = userDetail.getUserName();
	        String hashedPassword = userDetail.getPassword();
	  		String securUuid = UUID.randomUUID().toString();
			Long userId = userDao.saveLoginDetails(userName, hashedPassword);
			userDetail.setUserId(userId);
			userDetail.setSecurUuid(securUuid);
			
			if(userId==0 || userId == null){
				throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "Fail to save User Registration");
			}
			
			Long contactId = userDao.saveContactDetails(userDetail);
			userDetail.setContactId(contactId);
			if(!userDao.saveUserDetails(userDetail)){
				throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "Fail to save User Registration");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return userDetail.getSecurUuid();
    }
  
    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.manager.UserManager#getUserInfo(java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws UserException {
        logger.info("Obtaining user details for securuuid : {}", securUuid);
        UserDto user = null;
        List<SubjectDto> subjects=new ArrayList<SubjectDto>();
        AcademicInfoDto academicInfo = new AcademicInfoDto();
        ParentsInfoDto parentsInfo = new ParentsInfoDto();
        try {
            user = userDao.getUserInfo(securUuid);     
            
            if (user == null) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail to fetch registered user");
            }
            subjects=userDao.getUserSubjects(securUuid);
            if(!subjects.isEmpty()){
            	user.setSubjects(subjects);
            }    
            
            academicInfo = userDao.getAcademicInfo(securUuid);
            parentsInfo = userDao.getParentsInfo(securUuid);
            
            user.setAcademicInfo(academicInfo);
            user.setParentsInfo(parentsInfo);
            
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_FETCH_REGISTERD_USERS, "fail to fetch registered user", exception);
        }
        return user;
    }

   /* (non-Javadoc)
    * @see com.chalk.salt.dao.user.manager.UserManager#saveUserInfo(com.chalk.salt.common.dto.UserDto)
    */
   @Override
    public String saveUserInfo(final UserDto userDetails) throws UserException {
        logger.info("Saving user details of username : {}", userDetails.getUserName());
        String securUuid = null;
        if (isUserExist(userDetails.getUserName())) {
            throw new UserException(ErrorCode.USER_ALREADY_EXITS, "User with this user name already exists");
        }
        try {
            securUuid = registerUser(userDetails);

        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "fail_to_save_user_info", exception);
        }
        return securUuid;
    }


	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#updateProfile(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean updateProfile(final UserDto userDetails) throws UserException {
		logger.info("Updating user details of username : {}", userDetails.getUserName());    
	    UserDto user=null;
	    Boolean userStatus=false;
	    Boolean contactStatus=false;
	    AcademicInfoDto academicInfo=null;
	    ParentsInfoDto parentsInfo=null;
	    try {
	    	 if (userDetails!=null) {
	    		 academicInfo= userDetails.getAcademicInfo();
	    		 parentsInfo= userDetails.getParentsInfo();
	    		 userStatus=userDao.updateUserDetails(userDetails); 
	    	     user= userDao.getUserKeyDetails(userDetails.getSecurUuid());
	    	     userDetails.setContactId(user.getContactId());
	    		 contactStatus=userDao.updateContactDetails(userDetails);    		
	    		 if(academicInfo!=null && userStatus && contactStatus){
	    			 academicInfo.setAcademicInfoId(user.getAcademicId());
	    			 if(academicInfo.getAcademicInfoId()==null){
	    				 userDao.saveAcademicDetails(academicInfo);
	    			 } else{
	    				 userDao.updateAcademicDetails(academicInfo);
	    			 }
	    			 
	    		 }
	    		 if(parentsInfo!=null && userStatus && contactStatus){
	    			 parentsInfo.setParentId(user.getParentsId());
	    			 if(parentsInfo.getParentId()==null){
	    				 userDao.saveParentsDetails(parentsInfo);
	    			 } else {
	    				 userDao.updateParentsDetails(parentsInfo);
	    			 }
	    			 
	    		 }
	    	 }
	    } catch (final Exception exception) {
	        throw new UserException(ErrorCode.FAIL_TO_UPDATE_USER_INFO, "Fail to update user info", exception);
	    }
	    return (userStatus && contactStatus)?true:false;
	}
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#changePassword(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean changePassword(final UserDto userDetails) throws UserException {
		Boolean updateStatus = false; 
		if(userDetails!=null){
			try {
				final String userName = userDetails.getUserName();
				final String password = userDetails.getPassword();
				final String newPassword = userDetails.getNewPassword();
			
				updateStatus = userDao.changePassword(userName, password, newPassword);
			} catch (Exception exception) {
				throw new UserException(ErrorCode.FAIL_TO_UPDATE_USER_INFO, "Fail to change user password", exception);
			}		
		}
		return updateStatus;
	}
}
