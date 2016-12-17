package com.chalk.dust.core.system;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.dust.dao.system.manager.SystemManager;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.EmailNotificationDto;
import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.NotificationTemplateRequest;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.SystemEnquiryDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;
import com.chalk.salt.common.exceptions.TemplateProcessingException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.constant.NotificationTemplateKey;
import com.chalk.salt.core.templating.DatabaseTemplateConfiguration;
import com.chalk.salt.core.templating.TemplateConfig;
import com.chalk.salt.service.email.EmailService;

/**
 * The Class SystemFacadeImpl.
 */
public class SystemFacadeImpl implements SystemFacade {

	/** The system manager. */
	@Inject
	private SystemManager systemManager;

	/** The logger. */
	@Inject
	@AppLogger
	private Logger logger;
	
	/** The email service. */
	@Inject
    private EmailService emailService;
	
	/** The database template configuration. */
	@Inject
    @TemplateConfig
    private DatabaseTemplateConfiguration databaseTemplateConfiguration;
	
	 /** The bean mapper. */
 	@Inject
	 @BeanMapper
	 private Mapper beanMapper;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chalk.dust.core.system.SystemFacade#getUserClassesList()
	 */
	@Override
	public List<UserClassDto> getUserClassesList() throws SystemException {
		return systemManager.getUserClassesList();
	}

	/* (non-Javadoc)
	 * @see com.chalk.dust.core.system.SystemFacade#getSubjectsListByClassId(java.lang.String)
	 */
	@Override
	public List<SubjectDto> getSubjectsListByClassId(String classId)
			throws SystemException {
		return systemManager.getSubjectsListByClassId(classId);

	}

	/* (non-Javadoc)
	 * @see com.chalk.dust.core.system.SystemFacade#systemEnquiry(com.chalk.salt.common.dto.SystemEnquiryDto)
	 */
	@Override
	public Boolean systemEnquiry(SystemEnquiryDto systemEnquiryDetails)
			throws SystemException {		
	        final EmailNotificationDto emailNotification = getSystemEnquiryNotification(systemEnquiryDetails);
	        return emailService.sendEMailEnquiry(emailNotification);
	       
		
	}

	/**
	 * Gets the system enquiry notification.
	 *
	 * @param systemEnquiryDetails the system enquiry details
	 * @return the system enquiry notification
	 * @throws SystemException the system exception
	 */
	private EmailNotificationDto getSystemEnquiryNotification(
			SystemEnquiryDto systemEnquiryDetails) throws SystemException {
		final EmailNotificationDto emailNotification = new EmailNotificationDto();
        emailNotification.setTo("chalkandsalt@gmail.com");
        emailNotification.setSubject("New Enquiry");

        final Map<String, Object> systemEnquiryModel = beanMapper.map(systemEnquiryDetails, Map.class);

        String processedNotificationTemplate = null;
        try {
            final NotificationTemplateRequest notificationTemplateRequest = new NotificationTemplateRequest();
            notificationTemplateRequest.setTemplateKey(NotificationTemplateKey.NEW_ENQUIRY.name());
            notificationTemplateRequest.setDataMap(systemEnquiryModel);
            notificationTemplateRequest.setMergeBodyInTemplate(true);
            processedNotificationTemplate = databaseTemplateConfiguration.processTemplate(notificationTemplateRequest);

        } catch (final TemplateProcessingException e) {
            throw new SystemException(ErrorCode.ENQUIRY_SENT_FAILURE, "Enquiry not sent");
        }
        emailNotification.setBody(processedNotificationTemplate);
        return emailNotification;
	}

    /* (non-Javadoc)
     * @see com.chalk.dust.core.system.SystemFacade#getStudentsListByClassId(java.lang.String)
     */
    @Override
    public List<StudentsDto> getStudentsListByClassId(String classId) throws SystemException
        {
        return systemManager.getStudentsListByClassId(classId);
        }

    @Override
    public Long saveNotification(NotificationDto notificationDetails) throws SystemException
        {
            return systemManager.saveNotification(notificationDetails);
        }

    @Override
    public List<NotificationDto> getStudentNotificationList() throws SystemException
        {
            // TODO Auto-generated method stub
        return systemManager.getStudentNotificationList();
        }

    @Override
    public Long saveTestGroup(TestGroupDto testGroupDto) throws SystemException
        {
            // TODO Auto-generated method stub
            return systemManager.saveTestGroup(testGroupDto);
        }

    @Override
    public List<TestGroupDto> getTestGroupList() throws SystemException
        {
            // TODO Auto-generated method stub
            return systemManager.getTestGroupList();
        }

    @Override
    public List<NotificationDto> getAdminNotificationList() throws SystemException
        {
        return systemManager.getAdminNotificationList();
        }

    @Override
    public Boolean deleteAdminNotification(String notificationUuid) throws SystemException
        {
        return systemManager.deleteAdminNotification(notificationUuid);
        }

    @Override
    public Boolean deleteAdminTestGroup(String testGroupUuid) throws SystemException
        {
        return systemManager.deleteAdminTestGroup(testGroupUuid);
        }
}
