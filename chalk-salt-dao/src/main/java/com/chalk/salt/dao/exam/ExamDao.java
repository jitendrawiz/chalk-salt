package com.chalk.salt.dao.exam;

import java.util.List;

import com.chalk.salt.common.dto.DashBoardNotesDto;
import com.chalk.salt.common.dto.DashBoardVediosContentDto;
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

	/**
	 * Gets the question id using secur uuid.
	 *
	 * @param securUuid the secur uuid
	 * @return the question id using secur uuid
	 * @throws Exception the exception
	 */
	public Integer getQuestionIdUsingSecurUuid(String securUuid) throws Exception;

	/**
	 * Gets the previous question image.
	 *
	 * @param securUuid the secur uuid
	 * @return the previous question image
	 * @throws Exception the exception
	 */
	public String getPreviousQuestionImage(String securUuid) throws Exception;

	/**
	 * Update question image details.
	 *
	 * @param fileNameToSave the file name to save
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	public void updateQuestionImageDetails(String fileNameToSave, String securUuid) throws Exception;

	/**
	 * Gets the vedios list by class and subject id.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the vedios list by class and subject id
	 * @throws Exception the exception
	 */
	public List<DashBoardVediosContentDto> getVediosListByClassAndSubjectId(String classId, String subjectId)throws Exception;

    /**
     * Gets the notes list by class and subject id.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the notes list by class and subject id
     * @throws Exception the exception
     */
    public List<DashBoardNotesDto> getNotesListByClassAndSubjectId(String classId, String subjectId)throws Exception;

    /**
     * Gets the questions using type.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param limitOfQuestions the limit of questions
     * @return the questions using type
     * @throws Exception the exception
     */
    public List<QuestionDto> getQuestionsUsingType(String classId, String subjectId, int limitOfQuestions)throws Exception;

}
