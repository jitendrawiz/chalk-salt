/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

import com.chalk.salt.api.model.UserModel;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.UserDto;


/**
 * The Class UserService.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class UserService {

	/** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;
    /**
     * Validate user registration request.
     *
     * @param user the user
     * @return the map
     */
    public Map<String, String> validateUserRegistrationRequest(final UserModel user) {
        final Map<String, String> errors = new HashMap<String, String>();
        if (StringUtils.isBlank(user.getUserName())) {
            errors.put("username", "username can not be blank/null");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            errors.put("password", "password can not be blank/null");
        }
        if (StringUtils.isBlank(user.getConfirmPassword())) {
            errors.put("confirm password", "confirm password can not be blank/null");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.put("password mismatch", "password and confirm password should be same");
        }
        if (StringUtils.isBlank(user.getFirstName())) {
            errors.put("forename", "first name can not be blank/null");
        }
        if (StringUtils.isBlank(user.getLastName())) {
            errors.put("surname", "last name can not be blank/null");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            errors.put("emailAddress", "email address can not be blank/null");
        }
        
        return errors;
    }

    /**
     * Generate user detail list.
     *
     * @param users the users
     * @return the list
     */
    public List<Map<String, String>> generateUserDetailList(final List<UserDto> users) {
        final List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
        for (final UserDto user : users) {
            final Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("securUuid", user.getSecurUuid());
            userMap.put("forename", user.getFirstName());
            userMap.put("middle", user.getMiddleName());
            userMap.put("surname", user.getLastName());
            userList.add(userMap);
        }
        return userList;
    }
    
    public boolean logout() {
		try {
			logger.info("User Logout.....");
            final Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return true;
        } catch (final Exception ex) {
            throw ex;
        }
	}
}
