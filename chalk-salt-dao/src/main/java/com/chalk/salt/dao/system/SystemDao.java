package com.chalk.salt.dao.system;

import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.dto.SystemDetail;

/**
 * The Interface SystemDao.
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
