/**
 * 
 */
package com.chalk.salt.core.exam;

import java.util.List;

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

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#getQuestions(java.lang.String, java.lang.String)
	 */
	@Override
	public List<QuestionDto> getQuestions(String classId, String subjectId) throws ExamException {
		return examManager.getQuestions(classId, subjectId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#updateQuestionDetails(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String updateQuestionDetails(QuestionDto question) throws ExamException {
		return examManager.updateQuestionDetails(question);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#deleteQuestion(java.lang.String)
	 */
	@Override
	public Boolean deleteQuestion(String questionSecuruuid) throws ExamException {
		return examManager.deleteQuestion(questionSecuruuid);
	}

}
