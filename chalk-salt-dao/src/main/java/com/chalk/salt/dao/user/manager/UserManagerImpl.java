/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.GuestUserDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.ProfilePhotoUploadDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.TopicImageUploadDto;
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
	public String saveTopicRequest(DiscussionTopicRequestDto discussionDetails) throws UserException {
		
		logger.info("Saving topic request for user {}",discussionDetails.getSecurUuid());
        //check for already available topic requests.
        try {
        		discussionDetails.setRequestSecurUuid(UUID.randomUUID().toString());
        		return userDao.saveTopicRequest(discussionDetails);

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
		logger.info("Upload profile image");
        try {
        		System.out.println("Inside profile photo upload section");
        		String destPath = userDao.getSystemSettings("PROFILE_PHOTO");
        		Integer userId=userDao.getUserIdUsingSecurUuid(securUuid);
        		destPath +=  userId.toString();
        		File file = new File(destPath);
        		if (!file.exists()) {
        			file.mkdirs();
        		}
        		String fileName = "PROFILE_"+ userId.toString();//File Name to be saved without extension
        		String oldfileName=userDao.getUserProfilePhoto(securUuid); //File name already saved in database
        		String destPathToDeleteFile=null;
        		String fileNameToSave=fileName+"."+getExtension(documentUploadData.getName());
        		if(oldfileName!=null){
            		 destPathToDeleteFile=destPath+File.separator+fileName+"."+getExtension(oldfileName);
        		}else{
        			destPathToDeleteFile=destPath+File.separator+fileNameToSave;
        		}
        		destPath = destPath+File.separator+fileNameToSave;
        		File oldfile = new File(destPathToDeleteFile);
        		if(oldfile.exists()){
        			oldfile.delete();
        		}
        		FileInputStream fin=new FileInputStream(documentUploadData.getFile());  
        		FileOutputStream fout=new FileOutputStream(destPath);  
        		int i=0;  
        		while((i=fin.read())!=-1){  
        		fout.write((byte)i);  
        		}  
        		fin.close();
        		fout.close();        	
        		userDao.updateUserProfilePictureDetails(fileNameToSave,securUuid);
        		logger.info("Profile Photo updated successfully in database");
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_UPDATE_PROFILE_PHOTO, "Fail to update profile photo", exception);
        }
		return securUuid;
	}
	
	/**
	 * Gets the extension.
	 *
	 * @param fileName the file name
	 * @return the extension
	 */
	private String getExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension; 
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#getUserPhotoLink(java.lang.String)
	 */
	@Override
	public String getUserPhotoLink(String securUuid) throws UserException {
		String imagePath=null;
		 logger.info("Obtaining user details to make link of photo:", securUuid);
	        try {
	        	String destPath = userDao.getSystemSettings("PROFILE_PHOTO");
	            String profilePhoto = userDao.getUserProfilePhoto(securUuid);     
	            if (profilePhoto == null) {
	            	return profilePhoto;
	            }
	            Integer Id=userDao.getUserIdUsingSecurUuid(securUuid);
	            imagePath=destPath+Id.toString()+File.separator+profilePhoto;
	            logger.info("Image Path created is"+imagePath);
	        } catch (final Exception exception) {
	            throw new UserException(ErrorCode.FAIL_TO_FETCH_USER_IMAGE_PATH, "fail to fetch user image path", exception);
	        }
	        return imagePath;
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#deleteUserPhoto(java.lang.String)
	 */
	@Override
	public void deleteUserPhoto(String securUuid) throws UserException {

		logger.info("DELETING USER PHOTO :",securUuid);
        try {
        	
        	      String destPath=getUserPhotoLink(securUuid);   
        	      logger.info("DELETING USER PHOTO FROM SERVER LOCATION "+destPath);
        	      File file = new File(destPath);
        	      if(file.exists()){
        	    	  file.delete();
        	      }
        	      logger.info("FILE DELETED FROM LOCATION "+destPath);
        	      userDao.deleteUserPhotoFromDB(securUuid); 
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_DELETE_PROFILE_PHOTO, "Fail to delete profile photo", exception);
        }
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.user.manager.UserManager#uploadTopicImage(java.lang.String, com.chalk.salt.common.dto.TopicImageUploadDto)
	 */
	@Override
	public String uploadTopicImage(String securUuid,
			TopicImageUploadDto documentUploadData) throws UserException {
		logger.info("Uploading Topic image");
        try {
			System.out.println("Inside topic image upload section");
			String destPath = userDao.getSystemSettings("TOPIC_IMAGE");
			//Integer topicId = userDao.getTopicIdUsingSecurUuid(securUuid);
			destPath += securUuid.toString();
			File file = new File(destPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = "TOPIC_" + securUuid.toString();
			String oldfileName = userDao.getPreviousTopicImage(securUuid);
			String destPathToDeleteFile = null;
			String fileNameToSave = fileName + "."
					+ getExtension(documentUploadData.getName());
			if (oldfileName != null) {
				destPathToDeleteFile = destPath + File.separator + fileName
						+ "." + getExtension(oldfileName);
			} else {
				destPathToDeleteFile = destPath + File.separator
						+ fileNameToSave;
			}
			destPath = destPath + File.separator + fileNameToSave;
			File oldfile = new File(destPathToDeleteFile);
			if (oldfile.exists()) {
				oldfile.delete();
			}
			FileInputStream fin = new FileInputStream(
					documentUploadData.getFile());
			FileOutputStream fout = new FileOutputStream(destPath);
			int i = 0;
			while ((i = fin.read()) != -1) {
				fout.write((byte) i);
			}
			fin.close();
			fout.close();
			userDao.updateTopicImageDetails(fileNameToSave, securUuid);
			logger.info("Topic Image updated successfully in database");
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_UPDATE_TOPIC_IMAGE, "Fail to update topic image", exception);
        }
		return securUuid;
	}

	@Override
	public String getTopicImageLink(String securUuid) throws UserException {
		String imagePath=null;
		 logger.info("Obtaining topic details to make link of image:", securUuid);
	        try {
	        	String destPath = userDao.getSystemSettings("TOPIC_IMAGE");
	            String topicImage = userDao.getPreviousTopicImage(securUuid);     
	            if (topicImage == null) {
	            	return topicImage;
	            }
	            imagePath=destPath+securUuid.toString()+File.separator+topicImage;
	            logger.info("Image Path created is"+imagePath);
	        } catch (final Exception exception) {
	            throw new UserException(ErrorCode.FAIL_TO_FETCH_TOPIC_IMAGE_PATH, "fail to fetch topic image path", exception);
	        }
	        return imagePath;
	}

	@Override
	public String uploadTopicRequestImage(String securUuid, TopicImageUploadDto documentUploadData)
			throws UserException {
		logger.info("Uploading Topic Request image");
        try {
			System.out.println("Inside topic request image upload section");
			String destPath = userDao.getSystemSettings("TOPIC_IMAGE");
			destPath += securUuid.toString();
			File file = new File(destPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = "TOPIC_" + securUuid.toString();
			String oldfileName = userDao.getPreviousTopicRequestImage(securUuid);
			String destPathToDeleteFile = null;
			String fileNameToSave = fileName + "."
					+ getExtension(documentUploadData.getName());
			if (oldfileName != null) {
				destPathToDeleteFile = destPath + File.separator + fileName
						+ "." + getExtension(oldfileName);
			} else {
				destPathToDeleteFile = destPath + File.separator
						+ fileNameToSave;
			}
			destPath = destPath + File.separator + fileNameToSave;
			File oldfile = new File(destPathToDeleteFile);
			if (oldfile.exists()) {
				oldfile.delete();
			}
			FileInputStream fin = new FileInputStream(
					documentUploadData.getFile());
			FileOutputStream fout = new FileOutputStream(destPath);
			int i = 0;
			while ((i = fin.read()) != -1) {
				fout.write((byte) i);
			}
			fin.close();
			fout.close();
			userDao.updateTopicRequestImageDetails(fileNameToSave, securUuid);
			logger.info("Topic Request Image updated successfully in database");
        } catch (final Exception exception) {
            throw new UserException(ErrorCode.FAIL_TO_UPDATE_TOPIC_IMAGE, "Fail to update topic request image", exception);
        }
		return securUuid;	
	
	}

	@Override
	public GuestUserDto saveGuestUserDetails(GuestUserDto userDetails) throws UserException {
		logger.info("Registering new guest user ......");
		String securUuid = null;
        try {
	        securUuid = userDao.checkGuestUserExists(userDetails);
	        if(securUuid==null){
		  		securUuid = UUID.randomUUID().toString();	  		
				userDetails.setSecurUuid(securUuid);
				if(!userDao.saveGuestUserDetails(userDetails)){
					throw new UserException(ErrorCode.FAIL_TO_SAVE_USER_INFO, "Fail to save Guest User Registration");
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return userDetails;
	}
}
