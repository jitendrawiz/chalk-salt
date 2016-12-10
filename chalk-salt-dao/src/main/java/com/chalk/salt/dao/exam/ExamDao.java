package com.chalk.salt.dao.exam;

import java.util.Date;
import java.util.List;

import com.chalk.salt.common.dto.AnswerDto;
import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.DashBoardNotesDto;
import com.chalk.salt.common.dto.DashBoardVediosContentDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionOptionsDto;
import com.chalk.salt.common.dto.ResultContentDto;
import com.chalk.salt.common.dto.ResultMasterDto;
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;

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
	 * Gets the question secur uuid using id.
	 *
	 * @param id the id
	 * @return the question secur uuid using id
	 * @throws Exception the exception
	 */
	public String getQuestionSecurUuidUsingId(Integer id) throws Exception;

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
     * @param typeOfQuestion the type of question
     * @return the questions using type
     * @throws Exception the exception
     */
    public List<QuestionDto> getQuestionsUsingType(String classId, String subjectId, String typeOfQuestion,String testGroupId)throws Exception;

	/**
	 * Save question options.
	 *
	 * @param optionDto1 the option dto1
	 * @throws Exception the exception
	 */
	public void saveQuestionOptions(QuestionOptionsDto optionDto1) throws Exception;

	/**
	 * Gets the question options using question id.
	 *
	 * @param questionId the question id
	 * @return the question options using question id
	 * @throws Exception the exception
	 */
	public List<QuestionOptionsDto> getQuestionOptionsUsingQuestionId(
			Integer questionId)throws Exception;

	/**
	 * Gets the question id using secur uuid.
	 *
	 * @param questionSecuruuid the question securuuid
	 * @return the question id using secur uuid
	 * @throws Exception the exception
	 */
	public String getQuestionIdUsingSecurUuid(String questionSecuruuid)throws Exception;

	/**
	 * Delete question options.
	 *
	 * @param questionId the question id
	 * @throws Exception the exception
	 */
	public void deleteQuestionOptions(String questionId)throws Exception;

	/**
	 * Save answer details.
	 *
	 * @param answer the answer
	 * @param testId the test id
	 * @throws Exception the exception
	 */
	public void saveAnswerDetails(AnswerDto answer, String testId)throws Exception;

	/**
	 * Save answer test record.
	 *
	 * @param answers the answers
	 * @return the string
	 * @throws Exception the exception
	 */
	public String saveAnswerTestRecord(AnswersDto answers)throws Exception;

	/**
	 * Gets the test type list.
	 *
	 * @return the test type list
	 * @throws Exception the exception
	 */
	public List<TestTypeDto> getTestTypeList()throws Exception;

	/**
	 * Save schedule test data.
	 *
	 * @param scheduleTestDetails the schedule test details
	 * @param date the date
	 * @return the string
	 * @throws Exception the exception
	 */
	public String saveScheduleTestData(ScheduleTestDto scheduleTestDetails,
			Date date)throws Exception;

	/**
	 * Gets the schedule tests list using ids.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the schedule tests list using ids
	 * @throws Exception the exception
	 */
	public List<ScheduleTestDto> getScheduleTestsListUsingIds(String classId,
			String subjectId)throws Exception;

	/**
	 * Gets the schedule test content by id.
	 *
	 * @param scheduleTestUuid the schedule test uuid
	 * @return the schedule test content by id
	 * @throws Exception the exception
	 */
	public ScheduleTestDto getScheduleTestContentById(String scheduleTestUuid)throws Exception;

	/**
	 * Update schedule test content details.
	 *
	 * @param scheduleTestContentDetails the schedule test content details
	 * @param date the date
	 * @throws Exception the exception
	 */
	public void updateScheduleTestContentDetails(
			ScheduleTestDto scheduleTestContentDetails, Date date)throws Exception;

	/**
	 * Delete schedule test content data.
	 *
	 * @param scheduleTestUuid the schedule test uuid
	 * @throws Exception the exception
	 */
	public void deleteScheduleTestContentData(String scheduleTestUuid)throws Exception;

	/**
	 * Check list of schedule test.
	 *
	 * @param scheduleTestDetails the schedule test details
	 * @param date the date
	 * @param scheduleTestUuid the schedule test uuid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ScheduleTestDto> checkListOfScheduleTest(
			ScheduleTestDto scheduleTestDetails, Date date, String scheduleTestUuid)throws Exception;

	/**
	 * Gets the test duration from test type uuid.
	 *
	 * @param testTypeUuid the test type uuid
	 * @return the test duration from test type uuid
	 * @throws Exception the exception
	 */
	public String getTestDurationFromTestTypeUuid(String testTypeUuid)throws Exception;

    /**
     * Gets the schedule tests list using class id.
     *
     * @param classId the class id
     * @param studentId the student id
     * @return the schedule tests list using class id
     * @throws Exception the exception
     */
    public List<ScheduleTestDto> getScheduleTestsListUsingClassId(String classId, String studentId)throws Exception;

	/**
	 * Gets the result list by class and subject id.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param securUuid the secur uuid
	 * @param testUuid the test uuid
	 * @return the result list by class and subject id
	 * @throws Exception the exception
	 */
	public List<ResultContentDto> getResultDetailsByTestUuid(String classId, String subjectId, String securUuid, String testUuid,String testGroupId) throws Exception;

	
	/**
	 * Gets the results by class subject.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param securUuid the secur uuid
	 * @return the results by class subject
	 * @throws Exception the exception
	 */
	public List<ResultMasterDto> getResultsByClassSubject(String classId, String subjectId, String securUuid)throws Exception;

}
