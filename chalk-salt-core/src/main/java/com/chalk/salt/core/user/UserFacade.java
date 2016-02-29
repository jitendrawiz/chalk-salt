/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.user;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserFacade.
 */
public interface UserFacade {

	/**
	 * Gets the user info.
	 *
	 * @param securUuid the secur uuid
	 * @return the user info
	 * @throws UserException the user exception
	 */
	UserDto getUserInfo(final String securUuid) throws UserException;

	/**
	 * Save user info.
	 *
	 * @param userDetails the user details
	 * @return the string
	 * @throws UserException the user exception
	 */
	String saveUserInfo(final UserDto userDetails) throws UserException;
	
	/**
	 * Update profile.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean updateProfile(final UserDto userDetails)throws UserException;

	/**
	 * Change password.
	 *
	 * @param userDetails the user details
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean changePassword(final UserDto userDetails)throws UserException;

	/**
	 * Gets the students.
	 *
	 * @return the students
	 * @throws UserException the user exception
	 */
	List<UserDto> getStudents()throws UserException;

	/**
	 * Delete student.
	 *
	 * @param securUuid the secur uuid
	 * @return the boolean
	 * @throws UserException the user exception
	 */
	Boolean deleteStudent(String securUuid)throws UserException;

	/**
	 * Save topic request.
	 *
	 * @param discussionDetails the discussion details
	 * @throws UserException the user exception
	 */
	void saveTopicRequest(DiscussionTopicRequestDto discussionDetails)throws UserException;

}
