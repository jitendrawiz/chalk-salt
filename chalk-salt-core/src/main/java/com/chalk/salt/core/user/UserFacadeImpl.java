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
import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.EmailNotificationDto;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.core.freemarker.template.TemplateClient;
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
    private TemplateClient templateClient;
    
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
     * @see
     * uk.co.techblue.propco.enterprise.common.facade.interfaces.UserFacade#saveLoginStatus(uk.co.techblue.propco.enterprise
     * .common.dto.SaveLoginRequest)
     */
    @Override
    public boolean saveLoginStatus(final SaveLoginRequestDto saveLoginRequest) throws UserException {
        return userManager.saveLoginStatus(saveLoginRequest);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.core.user.UserFacade#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String username) throws UserException {
                return userManager.isUserExist(username);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.core.user.UserFacade#registerUser(java.lang.String, com.chalk.salt.common.dto.UserDetail,
     * java.lang.String)
     */
    @Override
    public String registerUser(final DomainUserPrincipalDto domainDto, final UserDto user, final String senderEmail) throws UserException {
        final String securId = userManager.registerUser(domainDto, user);              
        final EmailNotificationDto emailNotification = getEmailNotification(domainDto, user, senderEmail);
        emailService.sendMail(emailNotification);
        return securId;
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
    private EmailNotificationDto getEmailNotification(final DomainUserPrincipalDto domainDto, final UserDto user, final String senderEmail) throws UserException {
        final EmailNotificationDto emailNotification = new EmailNotificationDto();        
        emailNotification.setTo(user.getEmail());
        emailNotification.setFrom(senderEmail);
        emailNotification.setSubject("New User created over PropCo Enterprise");
        final String key = "user_registration_email";
        Map<String, Object> userDataModel = beanMapper.map(user, Map.class);
        String emailBody = templateClient.getNotificationMessage(userDataModel, key, domainDto);
        emailNotification.setBody(emailBody);
        return emailNotification;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.core.user.UserFacade#getDomainList(java.lang.String, java.lang.String)
     */
    @Override
    public List<DomainDetailDto> fetchDomains(final String securUuid, final String systemJndi) throws UserException {
        return userManager.fetchDomains(securUuid, systemJndi);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.core.user.UserFacade#fetchUserIconList(java.lang.String, java.lang.String)
     */
    @Override
    public List<UserIconDto> fetchIcons(final String securUuid, final String officeJndi) throws UserException {
        return userManager.fetchIcons(securUuid, officeJndi);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.core.user.UserFacade#saveUserHomeIcons(java.lang.String, java.lang.String,
     * com.chalk.salt.common.dto.UserHomeIcon)
     */
    @Override
    public boolean saveIcons(final String securId, final String officeJndi, final List<UserIconDto> userIcon) throws UserException {
        return userManager.saveIcons(securId, officeJndi, userIcon);
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#saveRejectedUser(java.lang.String, java.util.List, com.chalk.salt.common.dto.AuthStatus)
     */
    @Override
    public boolean saveRejectedUserDetails(final String username, final List<String> ipAddresses, final AuthStatus status) throws UserException {        
        return userManager.saveRejectedUserDetails(username, ipAddresses, status);
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#fetchUsers(java.lang.String)
     */
    @Override
    public List<UserDto> fetchUsers(final String officeJndi, final String systemJndi) throws UserException {
        return userManager.fetchUsers(officeJndi, systemJndi);
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#disableUser(java.lang.String, java.lang.String)
     */
    @Override
    public void disableUser(final String securUuid, final String disableDate) throws UserException {
        userManager.disableUser(securUuid, disableDate);        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid, final String officeJndi) throws UserException {
        return userManager.getUserInfo(securUuid, officeJndi);
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.core.user.UserFacade#saveUserInfo(com.chalk.salt.common.dto.UserDto, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String saveUserInfo(final UserDto userDetails, final DomainUserPrincipalDto domainDto) throws UserException {
        return userManager.saveUserInfo(userDetails, domainDto);
    }

}
