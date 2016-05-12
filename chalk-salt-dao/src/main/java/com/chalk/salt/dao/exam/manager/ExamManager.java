/**
 * 
 */
package com.chalk.salt.dao.exam.manager;

import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.exceptions.ExamException;

/**
 * The Interface ExamManager.
 *
 * @author jitendra
 */
public interface ExamManager {

	/**
	 * Save question.
	 *
	 * @param questionDetails the question details
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String saveQuestion(QuestionDto questionDetails)throws ExamException;

}
