package com.chalk.dust.core.system;

import java.util.List;

import com.chalk.salt.common.dto.SubjectDto;
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

}
