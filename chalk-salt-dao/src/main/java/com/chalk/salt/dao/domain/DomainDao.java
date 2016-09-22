
package com.chalk.salt.dao.domain;

import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

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
