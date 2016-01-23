package com.chalk.dust.dao.system.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.dust.dao.system.lookup.SystemLookupDao;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.SubjectDto;
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

}
