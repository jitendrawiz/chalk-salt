/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.ProfilePhotoUploadDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.user.UserDao;

// TODO: Auto-generated Javadoc
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
			if(userDetail.getStudentClass()!=null){
				AcademicInfoDto academicInfo =new AcademicInfoDto();
				academicInfo.setStudentClassId(Integer.parseInt(userDetail.getStudentClass()));
				academicInfo.setPercentage(0.0f);
				academicInfo.setPreviousSchool(null);
			Long academicId=userDao.saveAcademicDetails(academicInfo);
			userDetail.setAcademicId(academicId);
			}
			if(userDetail.getParentsId()==null){
				ParentsInfoDto parentsInfoDto=new ParentsInfoDto();
				userDetail.setParentsInfo(parentsInfoDto);
				Long parentsId=userDao.saveParentsDetails(parentsInfoDto);
				userDetail.setParentsId(parentsId);
			}
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
	    				 userDao.updateAcademicDetails(academicInfo);
	    		 }
	    		 if(parentsInfo!=null && userStatus && contactStatus){
	    			 parentsInfo.setParentId(user.getParentsId());
	    				 userDao.updateParentsDetails(parentsInfo);
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

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#getStudents()
	 */
	@Override
	public List<UserDto> getStudents() throws UserException {
		logger.info("Obtaining list of students......");
        List<UserDto> students = null;
        try {
        	students = userDao.getStudents();     
            
            if (students == null) {
                throw new UserException(ErrorCode.FAIL_TO_FETCH_STUDENTS_LIST, "fail to fetch students list");
            }
        } catch (final Exception exception) {
        	throw new UserException(ErrorCode.FAIL_TO_FETCH_STUDENTS_LIST, "fail to fetch students list", exception);
        }
        return students;
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#deleteStudent(java.lang.String)
	 */
	@Override
	public Boolean deleteStudent(String securUuid) throws UserException {
		logger.info("delete student with his contact details, parents, academic details and comments using secur uuid...");
		try{			
			userDao.deleteContact(securUuid);
			userDao.deleteParents(securUuid);
			userDao.deleteAcademic(securUuid);
			userDao.deleteTopicComment(securUuid);
			userDao.deleteLogin(securUuid);
			userDao.deleteStudent(securUuid);
			return true; // need to be discussed how to implement.
		} catch (final Exception exception) {
        	throw new UserException(ErrorCode.FAIL_TO_DELETE_STUDENT, "fail to delete student", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#saveTopicRequest(com.chalk.salt.common.dto.DiscussionTopicRequestDto)
	 */
	@Override
	public void saveTopicRequest(DiscussionTopicRequestDto discussionDetails) throws UserException {
		
		logger.info("Saving topic request for user {}",discussionDetails.getSecurUuid());
        //check for already available topic requests.
        try {
        		discussionDetails.setRequestSecurUuid(UUID.randomUUID().toString());
        		userDao.saveTopicRequest(discussionDetails);

        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_SAVE_TOPIC_REQUEST, "FAil to save topic request", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#uploadProfilePhoto(java.lang.String, com.chalk.salt.common.dto.ProfilePhotoUploadDto)
	 */
	@Override
	public String uploadProfilePhoto(String securUuid,
			ProfilePhotoUploadDto documentUploadData) throws UserException {
		logger.info("Update profile image");
        try {
        		System.out.println("Inside profile photo upload section");
        		String destPath = userDao.getSystemSettings("PROFILE_PHOTO");
        		destPath += "ProfilePhoto";
        		File file = new File(destPath);
        		if (!file.exists()) {
        			file.mkdirs();
        		}
        		
        		String fileName = "PROFILE_"+new Date().getTime();
        		destPath = destPath+"\\"+fileName+".jpg";
        		
        		FileInputStream fin=new FileInputStream(documentUploadData.getFile());  
        		FileOutputStream fout=new FileOutputStream(destPath);  
        		int i=0;  
        		while((i=fin.read())!=-1){  
        		fout.write((byte)i);  
        		}  
        		fin.close();
        		fout.close();
        		
        		//Continue your code here for photo upload now.
        		//userDao.saveTopicRequest(discussionDetails);

        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_UPDATE_PROFILE_PHOTO, "Fail to update profile photo", exception);
        }
		return "Done";
	}
	
	String getExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension; 
	}
}
