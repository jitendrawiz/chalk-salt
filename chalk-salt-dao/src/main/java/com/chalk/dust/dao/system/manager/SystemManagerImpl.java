package com.chalk.dust.dao.system.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.dust.dao.system.lookup.SystemLookupDao;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;
import com.chalk.salt.common.util.ErrorCode;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemManagerImpl.
 */
public class SystemManagerImpl implements SystemManager {

	/** The system lookup dao. */
	@Inject
	private SystemLookupDao systemLookupDao;

	/** The logger. */
	@Inject
	@AppLogger
	private Logger logger;

	/* (non-Javadoc)
	 * @see com.chalk.dust.dao.system.manager.SystemManager#getUserClassesList()
	 */
	@Override
	public List<UserClassDto> getUserClassesList() throws SystemException {
		logger.info("Obtaining the list of classe");
		List<UserClassDto> classes = new ArrayList<UserClassDto>();
		try {
			classes = systemLookupDao.getUserClassesList();
		} catch (final Exception exception) {
			throw new SystemException(ErrorCode.FAIL_TO_FETCH_CLASSES,
					"fail to fetch classes list", exception);
		}

		return classes;
	}

	/* (non-Javadoc)
	 * @see com.chalk.dust.dao.system.manager.SystemManager#getSubjectsListByClassId(java.lang.String)
	 */
	@Override
	public List<SubjectDto> getSubjectsListByClassId(String classId)
			throws SystemException {
		logger.info("Obtaining the list of subjects");
		List<SubjectDto> subjects = new ArrayList<SubjectDto>();
		try {
			subjects = systemLookupDao.getSubjectsListByClassId(classId);
		} catch (final Exception exception) {
			throw new SystemException(ErrorCode.FAIL_TO_FETCH_SUBJECTS,
					"fail to fetch subjects", exception);
		}

		return subjects;
}

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.manager.SystemManager#getStudentsListByClassId(java.lang.String)
     */
    @Override
    public List<StudentsDto> getStudentsListByClassId(String classId) throws SystemException
        {
        logger.info("Obtaining the list of students");
        List<StudentsDto> students = new ArrayList<StudentsDto>();
        try {
            students = systemLookupDao.getStudentsListByClassId(classId);
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_FETCH_STUDENTS,
                    "fail to fetch students", exception);
        }

        return students;
        
        }

    @Override
    public Long saveNotification(NotificationDto notificationDetails) throws SystemException
        {
        logger.info("Saving notification details");
        Long isSaved = null;
        try {
        notificationDetails.setNotificationUuid(UUID.randomUUID().toString());
        isSaved = systemLookupDao.saveNotification(notificationDetails);
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_SAVE_NOTIFICATION_INFO, "fail to save botification info", exception);
        }
        return isSaved;
        }

    @Override
    public List<NotificationDto> getStudentNotificationList() throws SystemException
        {
        logger.info("Obtaining the list of notifications");
        List<NotificationDto> list = new ArrayList<NotificationDto>();
        try {
        
        list = systemLookupDao.getStudentNotificationList();
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_FETCH_NOTIFICATIONS,
                    "fail to fetch notifications", exception);
        }

        return list;

        }

    @Override
    public Long saveTestGroup(TestGroupDto testGroupDto) throws SystemException
        {
        logger.info("Saving test group details");
        Long isSaved = null;
        try {
        testGroupDto.setTestGroupUuid(UUID.randomUUID().toString());
        isSaved = systemLookupDao.saveTestGroup(testGroupDto);
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_SAVE_TESTGROUP_INFO, "fail to save test group info", exception);
        }
        return isSaved;
        
        }

    @Override
    public List<TestGroupDto> getTestGroupList() throws SystemException
        {
        logger.info("Obtaining the list of test groups");
        List<TestGroupDto> list = new ArrayList<TestGroupDto>();
        try {
        
        list = systemLookupDao.getTestGroupList();
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_FETCH_TESTGROUPS,
                    "fail to fetch test groups", exception);
        }
        return list;
        }

    @Override
    public List<NotificationDto> getAdminNotificationList() throws SystemException
        {

        logger.info("Obtaining the list of notifications for admin");
        List<NotificationDto> list = new ArrayList<NotificationDto>();
        try {
        
        list = systemLookupDao.getAdminNotificationList();
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_FETCH_NOTIFICATIONS,
                    "fail to fetch notifications", exception);
        }
        return list;

        }

    @Override
    public Boolean deleteAdminNotification(String notificationUuid) throws SystemException
        {
        logger.info("deleting admin notification using notificiationuuid {}",notificationUuid);
        Boolean isDeleled=false;
        try {
        isDeleled = systemLookupDao.deleteAdminNotification(notificationUuid);
        } catch (final Exception exception) {
            throw new SystemException(ErrorCode.FAIL_TO_DELETE_NOTIFICATION,
                    "fail to delete notifications", exception);
        }
        return isDeleled;

        }

}
