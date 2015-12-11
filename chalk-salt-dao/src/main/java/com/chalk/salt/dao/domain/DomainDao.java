/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.domain;

import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface DomainDao.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public interface DomainDao {

    /**
     * Obtain user domain details.
     *
     * @param username the username
     * @return the user detail
     * @throws UserException the user exception
     */
    UserDto obtainUserLoginDetails(final String username) throws UserException;

}
