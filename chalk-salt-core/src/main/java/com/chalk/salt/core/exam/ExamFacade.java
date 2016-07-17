/**
 * 
 */
package com.chalk.salt.core.exam;

import java.util.List;

import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.DashBoardDataDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionImageUploadDto;
import com.chalk.salt.common.dto.QuestionListDto;
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;
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

	/**
	 * Gets the questions.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the questions
	 * @throws ExamException the exam exception
	 */
	List<QuestionDto> getQuestions(String classId, String subjectId) throws ExamException;

	/**
	 * Update question details.
	 *
	 * @param question the question
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String updateQuestionDetails(QuestionDto question) throws ExamException;

	/**
	 * Delete question.
	 *
	 * @param questionSecuruuid the question securuuid
	 * @return the boolean
	 * @throws ExamException the exam exception
	 */
	Boolean deleteQuestion(String questionSecuruuid) throws ExamException;

	/**
	 * Upload question image.
	 *
	 * @param securUuid the secur uuid
	 * @param documentUploadData the document upload data
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String uploadQuestionImage(String securUuid, QuestionImageUploadDto documentUploadData)throws ExamException;

	/**
	 * Gets the dash board data.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the dash board data
	 * @throws ExamException the exam exception
	 */
	DashBoardDataDto getDashBoardData(String classId, String subjectId)throws ExamException;

    
    /**
     * Gets the questions using type.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param type the type
     * @return the questions using type
     * @throws ExamException the exam exception
     */
    List<QuestionListDto> getQuestionsUsingType(String classId, String subjectId, String type)throws ExamException;

	/**
	 * Save answer details.
	 *
	 * @param answer the answer
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String saveAnswerDetails(AnswersDto answer)throws ExamException;

	/**
	 * Gets the test type list.
	 *
	 * @return the test type list
	 * @throws ExamException the exam exception
	 */
	List<TestTypeDto> getTestTypeList()throws ExamException;

	/**
	 * Save schedule test data.
	 *
	 * @param scheduleTestDetails the schedule test details
	 * @return the string
	 * @throws ExamException the exam exception
	 */
	String saveScheduleTestData(ScheduleTestDto scheduleTestDetails)throws ExamException;

}
