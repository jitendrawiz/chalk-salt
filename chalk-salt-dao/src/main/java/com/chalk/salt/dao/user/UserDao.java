/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user;

import java.util.List;

import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserDto;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Fetch user details.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user dto
	 * @throws Exception
	 *             the exception
	 */
	UserDto fetchUserDetails(final String securUuid) throws Exception;

	/**
	 * Checks if is user exist.
	 *
	 * @param userName
	 *            the user name
	 * @return true, if is user exist
	 * @throws Exception
	 *             the exception
	 */
	boolean isUserExist(final String userName) throws Exception;

	/**
	 * Save login details.
	 *
	 * @param username
	 *            the username
	 * @param hashedPassword
	 *            the hashed password
	 * @return the long
	 * @throws Exception
	 *             the exception
	 */
	Long saveLoginDetails(final String username, final String hashedPassword)
			throws Exception;

	/**
	 * Save user details.
	 *
	 * @param userDetail
	 *            the user detail
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	boolean saveUserDetails(final UserDto userDetail) throws Exception;

	/**
	 * Gets the user id by secur uuid.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user id by secur uuid
	 * @throws Exception
	 *             the exception
	 */
	Long getUserIdBySecurUuid(final String securUuid) throws Exception;

	/**
	 * Fetch users.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user dto
	 * @throws Exception
	 *             the exception
	 */
	UserDto fetchUsers(final String securUuid) throws Exception;

	/**
	 * Fetch allowed users.
	 *
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	List<String> fetchAllowedUsers() throws Exception;

	/**
	 * Disable user.
	 *
	 * @param userId
	 *            the user id
	 * @param disableDate
	 *            the disable date
	 * @throws Exception
	 *             the exception
	 */
	void disableUser(final Long userId, final String disableDate)
			throws Exception;

	/**
	 * Gets the user info.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user info
	 * @throws Exception
	 *             the exception
	 */
	UserDto getUserInfo(final String securUuid) throws Exception;

	/**
	 * Gets the id by uuid.
	 *
	 * @param tableName
	 *            the table name
	 * @param idFieldName
	 *            the id field name
	 * @param uuidFieldName
	 *            the uuid field name
	 * @param uuid
	 *            the uuid
	 * @return the id by uuid
	 * @throws Exception
	 *             the exception
	 */
	Long getIdByUuid(final String tableName, final String idFieldName,
			final String uuidFieldName, final String uuid) throws Exception;

	/**
	 * Save contact details.
	 *
	 * @param userDetail
	 *            the user detail
	 * @return the long
	 * @throws Exception
	 *             the exception
	 */
	Long saveContactDetails(UserDto userDetail) throws Exception;

	/**
	 * Gets the user subjects.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user subjects
	 * @throws Exception
	 *             the exception
	 */
	List<SubjectDto> getUserSubjects(String securUuid) throws Exception;

}
