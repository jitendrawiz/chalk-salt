/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user.manager;

import java.util.List;

import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Class AuthManager.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public interface UserManager {

    /**
     * Checks if is user exist.
     *
     * @param username the username
     * @return true, if is user exist
     * @throws UserException the core exception
     */
    boolean isUserExist(final String username) throws UserException;

    /**
     * Register user.
     *
     * @param domainDto the domain dto
     * @param user the user
     * @return the string
     * @throws UserException the core exception
     */
    public String registerUser(final DomainUserPrincipalDto domainDto, final UserDto user) throws UserException;

    /**
     * Fetch users.
     *
     * @return the list
     * @throws UserException the user exception
     */
    List<UserDto> fetchUsers() throws UserException;

    /**
     * Disable user.
     *
     * @param securUuid the secur uuid
     * @param disableDate the disable date
     * @throws UserException the user exception
     */
    void disableUser(final String securUuid, final String disableDate) throws UserException;

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
     * @param domainDto the domain dto
     * @return the string
     * @throws UserException the user exception
     */
    String saveUserInfo(final UserDto userDetails, final DomainUserPrincipalDto domainDto) throws UserException;
}
