package com.chalk.dust.core.system;

import java.util.List;

import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.SystemEnquiryDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SystemFacade.
 */
public interface SystemFacade {

	/**
	 * Gets the user classes list.
	 *
	 * @return the user classes list
	 * @throws SystemException
	 *             the system exception
	 */
	List<UserClassDto> getUserClassesList() throws SystemException;

	/**
	 * Gets the subjects list by class id.
	 *
	 * @param classId the class id
	 * @return the subjects list by class id
	 * @throws SystemException the system exception
	 */
	List<SubjectDto> getSubjectsListByClassId(String classId) throws SystemException;

	/**
	 * System enquiry.
	 *
	 * @param systemEnquiryDetails the system enquiry details
	 * @return the boolean
	 * @throws SystemException the system exception
	 */
	Boolean systemEnquiry(SystemEnquiryDto systemEnquiryDetails)throws SystemException;

    /**
     * Gets the students list by class id.
     *
     * @param classId the class id
     * @return the students list by class id
     * @throws SystemException the system exception
     */
    List<StudentsDto> getStudentsListByClassId(String classId)throws SystemException;

    /**
     * Save notification.
     *
     * @param notificationDetails the notification details
     * @return the boolean
     * @throws SystemException the system exception
     */
    Long saveNotification(NotificationDto notificationDetails)throws SystemException;

    /**
     * Gets the student notification list.
     *
     * @return the student notification list
     * @throws SystemException the system exception
     */
    List<NotificationDto> getStudentNotificationList()throws SystemException;

    /**
     * Save test group.
     *
     * @param testGroupDto the test group dto
     * @return the long
     * @throws SystemException the system exception
     */
    Long saveTestGroup(TestGroupDto testGroupDto)throws SystemException;

    List<TestGroupDto> getTestGroupList()throws SystemException;

}
