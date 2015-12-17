/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.EmailNotificationDto;
import com.chalk.salt.common.dto.NotificationTemplateRequest;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.TemplateProcessingException;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.constant.NotificationTemplateKey;
import com.chalk.salt.core.templating.DatabaseTemplateConfiguration;
import com.chalk.salt.core.templating.TemplateConfig;
import com.chalk.salt.dao.user.manager.UserManager;
import com.chalk.salt.service.email.EmailService;


/**
 * The Class UserFacadeImpl.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserFacadeImpl implements UserFacade {

    /** The user manager. */
    @Inject
    private UserManager userManager;

    /** The email service. */
    @Inject
    private EmailService emailService;

    /** The logger. */
    @Inject
    @AppLogger
    private Logger logger;

    /** The bean mapper. */
    @Inject
    @BeanMapper
    private Mapper beanMapper;

    /** The template client. */
    @Inject
    @TemplateConfig
    private DatabaseTemplateConfiguration databaseTemplateConfiguration;

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.common.facade.interfaces.UserFacade#getAllUserPermissions(java.lang.Long)
     */
    @Override
    public List<String> fetchAllUserPermissions(final Long userId) {
        final List<String> permissions = new ArrayList<String>();
        permissions.add("agent:domain:view");
        return permissions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.core.user.UserFacade#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String userName) throws UserException {
        return userManager.isUserExist(userName);
    }

    /**
     * Gets the email notification.
     *
     * @param domainDto the domain dto
     * @param user the user
     * @param senderEmail the sender email
     * @return the email notification
     * @throws UserException the user exception
     */

    private EmailNotificationDto getEmailNotification(final UserDto user) throws UserException {
        final EmailNotificationDto emailNotification = new EmailNotificationDto();
        emailNotification.setTo(user.getEmail());
       // emailNotification.setFrom(senderEmail);
        emailNotification.setSubject("New User created over Chalk N Dust");

        final Map<String, Object> userDataModel = beanMapper.map(user, Map.class);

        String processedNotificationTemplate = null;
        try {
            final NotificationTemplateRequest notificationTemplateRequest = new NotificationTemplateRequest();
            notificationTemplateRequest.setTemplateKey(NotificationTemplateKey.USER_REGISTRATION_SUCCESSFUL.name());
            notificationTemplateRequest.setDataMap(userDataModel);
            notificationTemplateRequest.setMergeBodyInTemplate(true);
            processedNotificationTemplate = databaseTemplateConfiguration.processTemplate(notificationTemplateRequest);

        } catch (final TemplateProcessingException e) {
            throw new UserException(ErrorCode.USER_REGISTRATION_FAILURE, "user_registration_failure");
        }
        emailNotification.setBody(processedNotificationTemplate);
        return emailNotification;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.core.user.UserFacade#fetchUsers(java.lang.String)
     */
    @Override
    public List<UserDto> fetchUsers() throws UserException {
        return userManager.fetchUsers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.core.user.UserFacade#disableUser(java.lang.String, java.lang.String)
     */
    @Override
    public void disableUser(final String securUuid, final String disableDate) throws UserException {
        userManager.disableUser(securUuid, disableDate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.core.user.UserFacade#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws UserException {
        return userManager.getUserInfo(securUuid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.core.user.UserFacade#saveUserInfo(uk.co.propco.common.dto.UserDto, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public String saveUserInfo(final UserDto userDetails) throws UserException {
        final String securUuid = userManager.saveUserInfo(userDetails);
        final EmailNotificationDto emailNotification = getEmailNotification(userDetails);
        emailService.sendMail(emailNotification);
        return securUuid;
    }

}
