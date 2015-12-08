/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.domain;

import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.dto.DomainInfo;

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
    DomainInfo obtainUserDomainDetails(final String username) throws UserException;

}
