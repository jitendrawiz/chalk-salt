/**
 * 
 */
package com.chalk.salt.core.exam;

import javax.inject.Inject;

import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.dao.exam.manager.ExamManager;

/**
 * The Class ExamFacadeImpl.
 *
 * @author jitendra
 */
public class ExamFacadeImpl implements ExamFacade {

	/** The exam manager. */
	@Inject
	private ExamManager examManager;
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#saveQuestion(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String saveQuestion(QuestionDto questionDetails) throws ExamException {
		return examManager.saveQuestion(questionDetails);
	}

}
