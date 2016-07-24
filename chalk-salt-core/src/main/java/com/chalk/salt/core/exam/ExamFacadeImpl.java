/**
 * 
 */
package com.chalk.salt.core.exam;

import java.util.List;

import javax.inject.Inject;

import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.DashBoardDataDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionImageUploadDto;
import com.chalk.salt.common.dto.QuestionListDto;
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;
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
		examManager.deleteQuestionImage(questionSecuruuid);
		return examManager.deleteQuestion(questionSecuruuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#uploadQuestionImage(java.lang.String, com.chalk.salt.common.dto.QuestionImageUploadDto)
	 */
	@Override
	public String uploadQuestionImage(String securUuid, QuestionImageUploadDto documentUploadData)
			throws ExamException {
		return examManager.uploadQuestionImage(securUuid,documentUploadData);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#getDashBoardData(java.lang.String, java.lang.String)
	 */
	@Override
	public DashBoardDataDto getDashBoardData(String classId, String subjectId) throws ExamException{
		return examManager.getDashBoardData(classId,subjectId);
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.core.exam.ExamFacade#getQuestionsUsingType(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<QuestionListDto> getQuestionsUsingType(String classId, String subjectId, String type,String scheduleTestUuid) throws ExamException
    {
        return examManager.getQuestionsUsingType(classId,subjectId,type,scheduleTestUuid);

    }

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#saveAnswerDetails(com.chalk.salt.common.dto.AnswersDto)
	 */
	@Override
	public String saveAnswerDetails(AnswersDto answer)throws ExamException {
		 return examManager.saveAnswerDetails(answer);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#getTestTypeList()
	 */
	@Override
	public List<TestTypeDto> getTestTypeList() throws ExamException {
		return examManager.getTestTypeList();
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#saveScheduleTestData(com.chalk.salt.common.dto.ScheduleTestDto)
	 */
	@Override
	public String saveScheduleTestData(ScheduleTestDto scheduleTestDetails)
			throws ExamException {
		return examManager.saveScheduleTestData(scheduleTestDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#getScheduleTestsListUsingIds(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ScheduleTestDto> getScheduleTestsListUsingIds(String classId,
			String subjectId) throws ExamException {
		return examManager.getScheduleTestsListUsingIds(classId,subjectId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#getScheduleTestContentById(java.lang.String)
	 */
	@Override
	public ScheduleTestDto getScheduleTestContentById(String scheduleTestUuid)
			throws ExamException {
		return examManager.getScheduleTestContentById(scheduleTestUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#updateScheduleTestContentDetails(com.chalk.salt.common.dto.ScheduleTestDto)
	 */
	@Override
	public void updateScheduleTestContentDetails(
			ScheduleTestDto scheduleTestContentDetails) throws ExamException {
		examManager.updateScheduleTestContentDetails(scheduleTestContentDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.exam.ExamFacade#deleteScheduleTestContentData(java.lang.String)
	 */
	@Override
	public Boolean deleteScheduleTestContentData(String scheduleTestUuid)
			throws ExamException {
		return examManager.deleteScheduleTestContentData(scheduleTestUuid);
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.core.exam.ExamFacade#getScheduleTestsListUsingClassId(java.lang.String, java.lang.String)
     */
    @Override
    public List<ScheduleTestDto> getScheduleTestsListUsingClassId(String classId,String studentId) throws ExamException
        {
        return examManager.getScheduleTestsListUsingClassId(classId,studentId);
        }

}
