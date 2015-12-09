/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.system;

import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.dto.SystemDetail;

/**
 * The Interface SystemDao.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public interface SystemDao {

    /**
     * Obtain user system details.
     *
     * @param systemJndiName the system jndi name
     * @param iRef the i ref
     * @return the system detail
     * @throws UserException the generic exception
     */
    SystemDetail obtainUserSystemDetails(String systemJndiName, Long iRef) throws UserException;

    /**
     * Checks if is IP address allowed.
     *
     * @param iRef the i ref
     * @param commaSeperatedIpAddress the comma seperated ip address
     * @param systemJndiName the system jndi name
     * @return true, if is IP address allowed
     * @throws UserException the generic exception
     */
    boolean isIPAddressAllowed(Long iRef, String commaSeperatedIpAddress, String systemJndiName) throws UserException;

}
