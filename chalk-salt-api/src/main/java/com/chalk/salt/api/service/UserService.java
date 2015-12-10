/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.chalk.salt.api.model.UserModel;
import com.chalk.salt.common.dto.UserDto;


/**
 * The Class UserService.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class UserService {

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
        if (StringUtils.isBlank(user.getForeName())) {
            errors.put("forename", "forename can not be blank/null");
        }
        if (StringUtils.isBlank(user.getSurName())) {
            errors.put("surname", "surname can not be blank/null");
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
            userMap.put("forename", user.getForeName());
            userMap.put("middle", user.getMiddleName());
            userMap.put("surname", user.getSurName());
            userList.add(userMap);
        }
        return userList;
    }
}
