/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user;

import java.util.List;

import com.chalk.salt.common.dto.AcademicInfoDto;
import com.chalk.salt.common.dto.ParentsInfoDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;

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
	Long saveContactDetails(UserDto userDetail) throws Exception;
	
	/**
	 * Gets the user subjects.
	 *
	 * @param securUuid the secur uuid
	 * @return the user subjects
	 * @throws Exception the exception
	 */
	List<SubjectDto> getUserSubjects(String securUuid) throws Exception;

	/**
	 * Gets the academic info.
	 *
	 * @param securUuid the secur uuid
	 * @return the academic info
	 * @throws Exception the exception
	 */
	AcademicInfoDto getAcademicInfo(String securUuid) throws Exception;

	/**
	 * Gets the parents info.
	 *
	 * @param securUuid the secur uuid
	 * @return the parents info
	 * @throws Exception the exception
	 */
	ParentsInfoDto getParentsInfo(String securUuid) throws Exception;

	/**
	 * Update academic details.
	 *
	 * @param academicInfo the academic info
	 * @throws Exception the exception
	 */
	void updateAcademicDetails(AcademicInfoDto academicInfo)throws Exception;

	/**
	 * Update parents details.
	 *
	 * @param parentsInfo the parents info
	 * @throws Exception the exception
	 */
	void updateParentsDetails(ParentsInfoDto parentsInfo)throws Exception;

	/**
	 * Update user details.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateUserDetails(UserDto userDetails)throws Exception;

	/**
	 * Update contact details.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws Exception the exception
	 */
	Boolean updateContactDetails(UserDto userDetails)throws Exception;

	UserDto getUserKeyDetails(String securUuid)throws Exception;

}
