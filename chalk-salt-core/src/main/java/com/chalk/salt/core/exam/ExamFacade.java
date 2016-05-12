/**
 * 
 */
package com.chalk.salt.core.exam;

import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.exceptions.ExamException;

/**
 * The Interface ExamFacade.
 *
 * @author jitendra
 */
public interface ExamFacade {

	/**
	 * Save question.
	 *
	 * @param questionDetails the question details
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String saveQuestion(QuestionDto questionDetails)throws ExamException;
}
