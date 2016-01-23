package com.chalk.dust.dao.system.lookup;

import java.util.List;

import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.UserClassDto;

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

}
