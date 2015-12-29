package com.chalk.dust.core.system;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.dust.dao.system.manager.SystemManager;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;

// TODO: Auto-generated Javadoc
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chalk.dust.core.system.SystemFacade#getUserClassesList()
	 */
	@Override
	public List<UserClassDto> getUserClassesList() throws SystemException {
		return systemManager.getUserClassesList();
	}

}
