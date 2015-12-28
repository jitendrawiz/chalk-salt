/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.user;

import java.util.List;

import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Class UserFacade.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public interface UserFacade {

	/**
	 * Gets the all user permissions.
	 *
	 * @param userId
	 *            the user id
	 * @return the all user permissions
	 */
	List<String> fetchAllUserPermissions(final Long userId);

	/**
	 * Checks if is user exits.
	 *
	 * @param username
	 *            the username
	 * @return true, if is user exits
	 * @throws UserException
	 *             the core exception
	 */
	boolean isUserExist(final String username) throws UserException;

	/**
	 * Fetch users.
	 *
	 * @param officeJndi
	 *            the office jndi
	 * @param systemJndi
	 *            the system jndi
	 * @return the list
	 * @throws UserException
	 *             the user exception
	 */
	List<UserDto> fetchUsers() throws UserException;

	/**
	 * Disable user.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @param disableDate
	 *            the disable date
	 * @throws UserException
	 *             the user exception
	 */
	void disableUser(final String securUuid, final String disableDate)
			throws UserException;

	/**
	 * Gets the user info.
	 *
	 * @param securUuid
	 *            the secur uuid
	 * @return the user info
	 * @throws UserException
	 *             the user exception
	 */
	UserDto getUserInfo(final String securUuid) throws UserException;

	String saveUserInfo(final UserDto userDetails) throws UserException;

}
