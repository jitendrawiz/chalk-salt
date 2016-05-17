/**
 * 
 */
package com.chalk.salt.dao.exam.manager;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.exam.ExamDao;

import ch.qos.logback.classic.pattern.Util;

/**
 * The Class ExamManagerImpl.
 *
 * @author jitendra
 */
public class ExamManagerImpl implements ExamManager {

	/** The exam dao. */
	@Inject
	private ExamDao examDao;
	
	/** The logger. */
    @Inject
    @AppLogger
    private Logger logger;
    
    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;
    
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#saveQuestion(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String saveQuestion(QuestionDto questionDetails) throws ExamException {
		logger.info("Saving Question ...");
		try{
			questionDetails.setQuestionSecuruuid(UUID.randomUUID().toString());
			
			return examDao.saveQuestion(questionDetails);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_SAVE_QUESTION, "Fail to Fetch Question", exception);
        }
	}

	@Override
	public List<QuestionDto> getQuestions(String classId, String subjectId) throws ExamException {
		logger.info("Fetching list of questions ...");
		try{
			return examDao.getQuestions(classId, subjectId);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_FETCH_QUESTION_LIST, "Fail to fetch question list", exception);
        }
	}

	@Override
	public String updateQuestionDetails(QuestionDto question) throws ExamException {
		logger.info("Updating Question ...");
		try{
			return examDao.updateQuestionDetails(question);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_UPDATE_QUESTION, "Fail to update question", exception);
        }
	}

}
