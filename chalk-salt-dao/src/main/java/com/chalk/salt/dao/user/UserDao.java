/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user;

import java.util.List;

import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {
	
	/**
	 * Checks if is user exist.
	 *
	 * @param userName the user name
	 * @return true, if is user exist
	 * @throws Exception the exception
	 */
	boolean isUserExist(final String userName) throws Exception;

	/**
	 * Save login details.
	 *
	 * @param username the username
	 * @param hashedPassword the hashed password
	 * @return the long
	 * @throws Exception the exception
	 */
	Long saveLoginDetails(final String username, final String hashedPassword)
			throws Exception;

	/**
	 * Save user details.
	 *
	 * @param userDetail the user detail
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean saveUserDetails(final UserDto userDetail) throws Exception;
	
	/**
	 * Gets the user info.
	 *
	 * @param securUuid the secur uuid
	 * @return the user info
	 * @throws Exception the exception
	 */
	UserDto getUserInfo(final String securUuid) throws Exception;

	/**
	 * Save contact details.
	 *
	 * @param userDetail the user detail
	 * @return the long
	 * @throws Exception the exception
	 */
	Long saveContactDetails(final UserDto userDetail) throws Exception;
	
	/**
	 * Gets the user subjects.
	 *
	 * @param securUuid the secur uuid
	 * @return the user subjects
	 * @throws Exception the exception
	 */
	List<SubjectDto> getUserSubjects(final String securUuid) throws Exception;

	/**
	 * Gets the academic info.
	 *
	 * @param securUuid the secur uuid
	 * @return the academic info
	 * @throws Exception the exception
	 */
	AcademicInfoDto getAcademicInfo(final String securUuid) throws Exception;

	/**
	 * Gets the parents info.
	 *
	 * @param securUuid the secur uuid
	 * @return the parents info
	 * @throws Exception the exception
	 */
	ParentsInfoDto getParentsInfo(final String securUuid) throws Exception;

	/**
	 * Update academic details.
	 *
	 * @param academicInfo the academic info
	 * @throws Exception the exception
	 */
	void updateAcademicDetails(final AcademicInfoDto academicInfo)throws Exception;

	/**
	 * Update parents details.
	 *
	 * @param parentsInfo the parents info
	 * @throws Exception the exception
	 */
	void updateParentsDetails(final ParentsInfoDto parentsInfo)throws Exception;

	/**
	 * Update user details.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateUserDetails(final UserDto userDetails)throws Exception;

	/**
	 * Update contact details.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateContactDetails(final UserDto userDetails)throws Exception;

	/**
	 * Gets the user key details.
	 *
	 * @param securUuid the secur uuid
	 * @return the user key details
	 * @throws Exception the exception
	 */
	UserDto getUserKeyDetails(final String securUuid)throws Exception;

	/**
	 * Change password.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param newPassword the new password
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean changePassword(final String userName, final String password, final String newPassword)throws Exception;

	/**
	 * Save academic details.
	 *
	 * @param academicInfo the academic info
	 * @return the long
	 * @throws Exception the exception
	 */
	Long saveAcademicDetails(AcademicInfoDto academicInfo)throws Exception;

	/**
	 * Save parents details.
	 *
	 * @param parentsInfo the parents info
	 * @return the long
	 * @throws Exception the exception
	 */
	Long saveParentsDetails(ParentsInfoDto parentsInfo)throws Exception;

	/**
	 * Gets the students.
	 *
	 * @return the students
	 * @throws Exception the exception
	 */
	List<UserDto> getStudents()throws Exception;

	/**
	 * Delete student.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteStudent(String securUuid)throws Exception;

	/**
	 * Delete contact.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteContact(String securUuid)throws Exception;

	/**
	 * Delete parents.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteParents(String securUuid)throws Exception;

	/**
	 * Delete academic.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteAcademic(String securUuid)throws Exception;

	/**
	 * Delete topic comment.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteTopicComment(String securUuid)throws Exception;

	/**
	 * Delete login.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteLogin(String securUuid)throws Exception;

	/**
	 * Save topic request.
	 *
	 * @param discussionDetails the discussion details
	 * @throws Exception the exception
	 */
	void saveTopicRequest(DiscussionTopicRequestDto discussionDetails)throws Exception;

	/**
	 * Gets the system settings.
	 *
	 * @param settingsKey the settings key
	 * @return the system settings
	 * @throws Exception the exception
	 */
	String getSystemSettings(String settingsKey)throws Exception;

	
	/**
	 * Gets the user id using secur uuid.
	 *
	 * @param securUuid the secur uuid
	 * @return the user id using secur uuid
	 * @throws Exception the exception
	 */
	Integer getUserIdUsingSecurUuid(String securUuid)throws Exception;

	/**
	 * Update user profile picture details.
	 *
	 * @param fileName the file name
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void updateUserProfilePictureDetails(String fileName, String securUuid)throws Exception;

	/**
	 * Gets the user profile photo.
	 *
	 * @param securUuid the secur uuid
	 * @return the user profile photo
	 * @throws Exception the exception
	 */
	String getUserProfilePhoto(String securUuid)throws Exception;

}
