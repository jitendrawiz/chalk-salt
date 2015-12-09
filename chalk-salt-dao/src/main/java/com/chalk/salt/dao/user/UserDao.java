/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.user;

import java.util.List;

import org.sql2o.data.Row;

import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDetailDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;

/**
 * The Interface UserDao.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public interface UserDao {

    /**
     * Fetch user details.
     *
     * @param securUuid the secur uuid
     * @param officeJndi the office jndi
     * @return the user detail
     * @throws Exception the exception
     */
    UserDetailDto fetchUserDetails(final String securUuid, final String officeJndi) throws Exception;

    /**
     * Save user login status.
     *
     * @param userId the user id
     * @param userName the user name
     * @param commaSeperatedIpAddress the comma seperated ip address
     * @param officeJndi the office jndi
     * @param status the status
     * @return true, if successful
     * @throws Exception the exception
     */
    boolean saveUserLoginStatus(Long userId, String userName, String commaSeperatedIpAddress, String officeJndi, String status) throws Exception;

    /**
     * Checks if is user exits.
     *
     * @param username the username
     * @return true, if is user exits
     * @throws Exception the exception
     */
    boolean isUserExist(final String username) throws Exception;

    /**
     * Fetch system master id.
     *
     * @param systemJndi the system jndi
     * @return the long
     * @throws Exception the exception
     */
    Long getMasterIdBySystemJndi(final String systemJndi) throws Exception;

    /**
     * Save user.
     *
     * @param username the username
     * @param hashedPassword the hashed password
     * @return the long
     * @throws Exception the exception
     */
    Long saveLoginDetails(final String username, final String hashedPassword) throws Exception;

    /**
     * Fetch assigend domains iref.
     *
     * @param systemJndi the system jndi
     * @param irefUuid the iref uuid
     * @return the list
     * @throws Exception the exception
     */
    List<Row> fetchSystemDataByIrefUuid(final String systemJndi, final String irefUuid) throws Exception;

    /**
     * Save user domain details.
     *
     * @param userId the user id
     * @param securUuid the secur uuid
     * @param masterId the master id
     * @param iref the iref
     * @param defaultDomain the default domain
     * @return true, if successful
     * @throws Exception the exception
     */
    boolean saveDomainDetail(final Long userId, final String securUuid, final Long masterId, final Integer iref, final boolean defaultDomain) throws Exception;

    /**
     * Save user details.
     *
     * @param userDetail the user detail
     * @param netname the netname
     * @return true, if successful
     * @throws Exception the exception
     */
    boolean saveUserDetails(final UserDto userDetail, final String netname) throws Exception;

    /**
     * Fetch iref.
     *
     * @param securUuid the secur uuid
     * @param systemJndi the system jndi
     * @return the list
     * @throws Exception the exception
     */
    List<Integer> fetchIrefBySystemJndi(final String securUuid, final String systemJndi) throws Exception;

    /**
     * Gets the domain detail by iref.
     *
     * @param systemJndi the system jndi
     * @param iref the iref
     * @return the domain detail by iref
     * @throws Exception the exception
     */
    List<DomainDetailDto> fetchDomainDetailByIref(final String systemJndi, final List<Integer> iref) throws Exception;

    /**
     * Fetch user icon list.
     *
     * @param securUuid the secur uuid
     * @param entJndiName the ent jndi name
     * @return the list
     * @throws Exception the exception
     */
    List<UserIconDto> fetchIcons(final String securUuid, final String entJndiName) throws Exception;

    /**
     * Save user home icons.
     *
     * @param userId the user id
     * @param officeJndi the office jndi
     * @param userIcon the user icon
     * @return true, if successful
     * @throws Exception the exception
     */
    boolean saveIcons(final Long userId, final String officeJndi, final UserIconDto userIcon) throws Exception;

    /**
     * Gets the user id by secur uuid.
     *
     * @param securUuid the secur uuid
     * @return the user id by secur uuid
     * @throws Exception the exception
     */
    Long getUserIdBySecurUuid(final String securUuid) throws Exception;
    
    /**
     * Gets the failed login attempts.
     *
     * @param officeJndi the office jndi
     * @param saveLoginRequest the save login request
     * @param currentDateTime the current date time
     * @param nextDateTime the next date time
     * @return the failed login attempts
     * @throws Exception the exception
     */
    int getFailedLoginAttempts(final String officeJndi, final SaveLoginRequestDto saveLoginRequest, final String currentDateTime, final String nextDateTime) throws Exception;
    
    /**
     * Lock user.
     *
     * @param officeJndi the office jndi
     * @param saveLoginRequest the save login request
     * @param currentDate the current date
     * @throws Exception the exception
     */
    void lockUser(final String officeJndi, final SaveLoginRequestDto saveLoginRequest, final String currentDate) throws Exception;
    
    
    /**
     * Fetch users.
     *
     * @param securUuid the secur uuid
     * @param officeJndi the office jndi
     * @return the list
     * @throws Exception the exception
     */
    UserDto fetchUsers(final String securUuid, final String officeJndi) throws Exception;
        
    /**
     * Fetch allowed users.
     *
     * @param iref the iref
     * @return the list
     * @throws Exception the exception
     */
    List<String> fetchAllowedUsers(Integer iref) throws Exception;
    
    /**
     * Fetch iref by office jndi.
     *
     * @param officeJndi the office jndi
     * @param systemJndi the system jndi
     * @return the list
     * @throws Exception the exception
     */
    Integer fetchIrefByOfficeJndi(final String officeJndi, final String systemJndi) throws Exception;


    /**
     * Disable user.
     *
     * @param userId the user id
     * @param disableDate the disable date
     * @throws Exception the exception
     */
    void disableUser(final Long userId, final String disableDate) throws Exception;

    /**
     * Gets the user info.
     *
     * @param securUuid the secur uuid
     * @param officeJndi the office jndi
     * @return the user info
     * @throws Exception the exception
     */
    UserDto getUserInfo(final String securUuid, final String officeJndi) throws Exception;
}
