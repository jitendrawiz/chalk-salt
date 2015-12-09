/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.core.user;

import java.util.List;

import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;
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
     * @param userId the user id
     * @return the all user permissions
     */
    List<String> fetchAllUserPermissions(final Long userId);

    /**
     * Save login status.
     *
     * @param saveLoginRequest the save login request
     * @return true, if successful
     * @throws UserException the generic exception
     */
    boolean saveLoginStatus(final SaveLoginRequestDto saveLoginRequest) throws UserException;

    /**
     * Checks if is user exits.
     *
     * @param username the username
     * @return true, if is user exits
     * @throws UserException the core exception
     */
    boolean isUserExist(final String username) throws UserException;

    /**
     * Register user.
     *
     * @param domainDto the domain dto
     * @param user the user
     * @param senderEmail the sender email
     * @return the string
     * @throws UserException the core exception
     */
    String registerUser(final DomainUserPrincipalDto domainDto, final UserDto user, final String senderEmail) throws UserException;

    /**
     * Gets the domain list.
     *
     * @param securUuid the secur uuid
     * @param systemJndi the system jndi
     * @return the domain list
     * @throws UserException the core exception
     */
    List<DomainDetailDto> fetchDomains(final String securUuid, final String systemJndi) throws UserException;

    /**
     * Fetch user icon list.
     *
     * @param securUuid the secur uuid
     * @param entJndiName the ent jndi name
     * @return the list
     * @throws UserException the core exception
     */
    List<UserIconDto> fetchIcons(final String securUuid, final String entJndiName) throws UserException;

    /**
     * Save user home icons.
     *
     * @param securId the secur id
     * @param officeJndi the office jndi
     * @param userIcon the user icon
     * @return true, if successful
     * @throws UserException the core exception
     */
    boolean saveIcons(final String securId, final String officeJndi, final List<UserIconDto> userIcon) throws UserException;
    
    /**
     * Save rejected user details.
     *
     * @param username the username
     * @param ipAddresses the ip addresses
     * @param status the status
     * @return true, if successful
     * @throws UserException the user exception
     */
    boolean saveRejectedUserDetails(final String username, final List<String>ipAddresses, final AuthStatus status) throws UserException;
    
    /**
     * Fetch users.
     *
     * @param officeJndi the office jndi
     * @param systemJndi the system jndi
     * @return the list
     * @throws UserException the user exception
     */
    List<UserDto> fetchUsers(final String officeJndi, final String systemJndi) throws UserException;

    /**
     * Disable user.
     *
     * @param securUuid the secur uuid
     * @param disableDate the disable date
     * @throws UserException the user exception
     */
    void disableUser(final String securUuid, final String disableDate)throws UserException;

    /**
     * Gets the user info.
     *
     * @param securUuid the secur uuid
     * @param officeJndi the office jndi
     * @return the user info
     * @throws UserException the user exception
     */
    UserDto getUserInfo(final String securUuid, final String officeJndi)throws UserException;

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
