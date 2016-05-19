/**
 * 
 */
package com.chalk.salt.dao.exam.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionImageUploadDto;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.exam.ExamDao;
import com.chalk.salt.dao.user.UserDao;

/**
 * The Class ExamManagerImpl.
 *
 * @author jitendra
 */
public class ExamManagerImpl implements ExamManager {

	/** The exam dao. */
	@Inject
	private ExamDao examDao;
	
	@Inject
	private UserDao userDao;
	
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

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#getQuestions(java.lang.String, java.lang.String)
	 */
	@Override
	public List<QuestionDto> getQuestions(String classId, String subjectId) throws ExamException {
		logger.info("Fetching list of questions ...");
		try{
			return examDao.getQuestions(classId, subjectId);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_FETCH_QUESTION_LIST, "Fail to fetch question list", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#updateQuestionDetails(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String updateQuestionDetails(QuestionDto question) throws ExamException {
		logger.info("Updating Question ...");
		try{
			final Date date = new Date();
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			question.setModifiedDate(modifiedDate);
			return examDao.updateQuestionDetails(question);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_UPDATE_QUESTION, "Fail to update question", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.manager.ExamManager#deleteQuestion(java.lang.String)
	 */
	@Override
	public Boolean deleteQuestion(String questionSecuruuid) throws ExamException {
		logger.info("Deleting Question ...");
		try{
			return examDao.deleteQuestion(questionSecuruuid);
		} catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_DELETE_QUESTION, "Fail to delete question", exception);
        }
	}

	@Override
	public String uploadQuestionImage(String securUuid, QuestionImageUploadDto documentUploadData)
			throws ExamException {
		logger.info("Uploading Question image");
        try {
			System.out.println("Inside Question image upload section");
			String destPath = userDao.getSystemSettings("QUESTION_IMAGE");
			destPath += securUuid.toString();
			File file = new File(destPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = "QUESTION_" + securUuid.toString();
			String oldfileName = examDao.getPreviousQuestionImage(securUuid);
			String destPathToDeleteFile = null;
			String fileNameToSave = fileName + "."
					+ getExtension(documentUploadData.getName());
			if (oldfileName != null) {
				destPathToDeleteFile = destPath + File.separator + fileName
						+ "." + getExtension(oldfileName);
			} else {
				destPathToDeleteFile = destPath + File.separator
						+ fileNameToSave;
			}
			destPath = destPath + File.separator + fileNameToSave;
			File oldfile = new File(destPathToDeleteFile);
			if (oldfile.exists()) {
				oldfile.delete();
			}
			FileInputStream fin = new FileInputStream(
					documentUploadData.getFile());
			FileOutputStream fout = new FileOutputStream(destPath);
			int i = 0;
			while ((i = fin.read()) != -1) {
				fout.write((byte) i);
			}
			fin.close();
			fout.close();
			examDao.updateQuestionImageDetails(fileNameToSave, securUuid);
			logger.info("Question Image updated successfully in database");
        } catch (final Exception exception) {
            throw new ExamException(ErrorCode.FAIL_TO_UPDATE_QUESTION_IMAGE, "Fail to update question image", exception);
        }
		return securUuid;
	}

	/**
	 * Gets the extension.
	 *
	 * @param fileName the file name
	 * @return the extension
	 */
	private String getExtension(String fileName){
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension; 
	}
}
