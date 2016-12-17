package com.chalk.dust.dao.system.lookup;

import java.util.List;

import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SystemLookupDao.
 */
public interface SystemLookupDao {

	/**
	 * Gets the user classes list.
	 *
	 * @return the user classes list
	 * @throws Exception
	 *             the exception
	 */
	List<UserClassDto> getUserClassesList() throws Exception;

	/**
	 * Gets the subjects list by class id.
	 *
	 * @param classId the class id
	 * @return the subjects list by class id
	 * @throws Exception the exception
	 */
	List<SubjectDto> getSubjectsListByClassId(String classId) throws Exception;

    /**
     * Gets the system settings.
     *
     * @param settingsKey the settings key
     * @return the system settings
     * @throws Exception the exception
     */
    String getSystemSettings(String settingsKey) throws Exception;

    /**
     * Gets the students list by class id.
     *
     * @param classId the class id
     * @return the students list by class id
     * @throws Exception the exception
     */
    List<StudentsDto> getStudentsListByClassId(String classId)throws Exception;

    /**
     * Save notification.
     *
     * @param notificationDetails the notification details
     * @return the boolean
     * @throws Exception the exception
     */
    Long saveNotification(NotificationDto notificationDetails)throws Exception;

    /**
     * Gets the student notification list.
     *
     * @return the student notification list
     * @throws Exception the exception
     */
    List<NotificationDto> getStudentNotificationList()throws Exception;

    /**
     * Save test group.
     *
     * @param testGroupDto the test group dto
     * @return the long
     * @throws Exception the exception
     */
    Long saveTestGroup(TestGroupDto testGroupDto)throws Exception;

    /**
     * Gets the test group list.
     *
     * @return the test group list
     * @throws Exception the exception
     */
    List<TestGroupDto> getTestGroupList()throws Exception;

    /**
     * Gets the admin notification list.
     *
     * @return the admin notification list
     * @throws Exception the exception
     */
    List<NotificationDto> getAdminNotificationList()throws Exception;

    /**
     * Delete admin notification.
     *
     * @param notificationUuid the notification uuid
     * @return the boolean
     * @throws Exception the exception
     */
    Boolean deleteAdminNotification(String notificationUuid)throws Exception;

    /**
     * Gets the questions latched on group.
     *
     * @param testGroupUuid the test group uuid
     * @return the questions latched on group
     * @throws Exception the exception
     */
    Long getQuestionsLatchedOnGroup(String testGroupUuid)throws SystemException;

    /**
     * Delete admin test group.
     *
     * @param testGroupUuid the test group uuid
     * @return the boolean
     * @throws Exception the exception
     */
    Boolean deleteAdminTestGroup(String testGroupUuid)throws Exception;

}
