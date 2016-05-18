package com.chalk.salt.dao.exam;

import java.util.List;

import com.chalk.salt.common.dto.QuestionDto;

/**
 * The Interface ExamDao.
 */
public interface ExamDao {

	/**
	 * Save question.
	 *
	 * @param questionDetails the question details
	 * @return the string
	 * @throws Exception the exception
	 */
	public String saveQuestion(QuestionDto questionDetails)throws Exception;

	/**
	 * Gets the questions.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the questions
	 * @throws Exception the exception
	 */
	public List<QuestionDto> getQuestions(String classId, String subjectId)throws Exception;

	/**
	 * Update question details.
	 *
	 * @param question the question
	 * @return the string
	 * @throws Exception the exception
	 */
	public String updateQuestionDetails(QuestionDto question) throws Exception;

	/**
	 * Delete question.
	 *
	 * @param questionSecuruuid the question securuuid
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deleteQuestion(String questionSecuruuid) throws Exception;

}
