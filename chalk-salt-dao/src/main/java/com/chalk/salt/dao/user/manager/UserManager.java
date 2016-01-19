/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface UserManager.
 */
public interface UserManager {
   
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
}
