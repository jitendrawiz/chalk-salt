/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.user;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.EmailNotificationDto;
import com.chalk.salt.common.dto.NotificationTemplateRequest;
import com.chalk.salt.common.dto.ProfilePhotoUploadDto;
import com.chalk.salt.common.dto.TopicImageUploadDto;
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

    
    /**
     * Gets the email notification.
     *
     * @param user the user
     * @return the email notification
     * @throws UserException the user exception
     */
    private EmailNotificationDto getEmailNotification(final UserDto user) throws UserException {
        final EmailNotificationDto emailNotification = new EmailNotificationDto();
        emailNotification.setTo(user.getEmail());
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
    
    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#getUserInfo(java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws UserException {
        return userManager.getUserInfo(securUuid);
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#saveUserInfo(com.chalk.salt.common.dto.UserDto)
     */
    @Override
    public String saveUserInfo(final UserDto userDetails) throws UserException {
        final String securUuid = userManager.saveUserInfo(userDetails);
        final EmailNotificationDto emailNotification = getEmailNotification(userDetails);
        emailService.sendMail(emailNotification);
        return securUuid;
    }

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#updateProfile(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean updateProfile(final UserDto userDetails) throws UserException {
		return userManager.updateProfile(userDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#changePassword(com.chalk.salt.common.dto.UserDto)
	 */
	@Override
	public Boolean changePassword(final UserDto userDetails) throws UserException {
		return userManager.changePassword(userDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#getStudents()
	 */
	@Override
	public List<UserDto> getStudents() throws UserException {
		return userManager.getStudents();
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#deleteStudent(java.lang.String)
	 */
	@Override
	public Boolean deleteStudent(String securUuid) throws UserException {
		return userManager.deleteStudent(securUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#saveTopicRequest(com.chalk.salt.common.dto.DiscussionTopicRequestDto)
	 */
	@Override
	public void saveTopicRequest(DiscussionTopicRequestDto discussionDetails) throws UserException {
		userManager.saveTopicRequest(discussionDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#uploadProfilePhoto(java.lang.String, com.chalk.salt.common.dto.ProfilePhotoUploadDto)
	 */
	@Override
	public String uploadProfilePhoto(String securUuid,
			ProfilePhotoUploadDto documentUploadData) throws UserException {
		return userManager.uploadProfilePhoto(securUuid,documentUploadData);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#getUserPhotoLink(java.lang.String)
	 */
	@Override
	public String getUserPhotoLink(String securUuid) throws UserException {
		return userManager.getUserPhotoLink(securUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#deleteUserPhoto(java.lang.String)
	 */
	@Override
	public void deleteUserPhoto(String securUuid) throws UserException {
		 userManager.deleteUserPhoto(securUuid);
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#uploadTopicImage(java.lang.String, com.chalk.salt.common.dto.TopicImageUploadDto)
	 */
	@Override
	public String uploadTopicImage(String securUuid,
			TopicImageUploadDto documentUploadData) throws UserException {
		return userManager.uploadTopicImage(securUuid,documentUploadData);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#getTopicImageLink(java.lang.String)
	 */
	@Override
	public String getTopicImageLink(String securUuid) throws UserException {
		return userManager.getTopicImageLink(securUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.user.UserFacade#uploadTopicRequestImage(java.lang.String, com.chalk.salt.common.dto.TopicImageUploadDto)
	 */
	@Override
	public String uploadTopicRequestImage(String securUuid, TopicImageUploadDto documentUploadData)
			throws UserException {
		return userManager.uploadTopicRequestImage(securUuid,documentUploadData);
	}

	

}
