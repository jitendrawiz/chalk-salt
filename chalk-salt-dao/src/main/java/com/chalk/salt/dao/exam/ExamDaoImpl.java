package com.chalk.salt.dao.exam;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.DashBoardVediosContentDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class ExamDaoImpl.
 */
public class ExamDaoImpl implements ExamDao {

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#saveQuestion(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String saveQuestion(QuestionDto questionDetails) throws Exception {
		final String sqlQuery = "INSERT INTO `cst_questions` (`class_id`, `subject_id`, `question`, "
				+ "`option1`, `option2`, `option3`, `option4`, `answer`, `question_uuid`)"
				+ "VALUES(:classId, :subjectId, :question, :optionA, :optionB, :optionC, :optionD, :answer, :questionUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", questionDetails.getClassId());
            query.addParameter("subjectId", questionDetails.getSubjectId());
            query.addParameter("question", questionDetails.getQuestion());
            query.addParameter("optionA", questionDetails.getOptionA());
            query.addParameter("optionB", questionDetails.getOptionB());
            query.addParameter("optionC", questionDetails.getOptionC());
            query.addParameter("optionD", questionDetails.getOptionD());
            query.addParameter("answer", questionDetails.getAnswer());
            query.addParameter("questionUuid", questionDetails.getQuestionSecuruuid());            
            query.executeUpdate();
            return questionDetails.getQuestionSecuruuid();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getQuestions(java.lang.String, java.lang.String)
	 */
	@Override
	public List<QuestionDto> getQuestions(String classId, String subjectId) throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `question`, `option1` as optionA, `option2`  as optionB, "
				+ "`option3`  as optionC, `option4`  as optionD, `answer`, `created_at` as creationDate, `modified_at` as modifiedDate, "
				+ "`question_uuid` as questionSecuruuid FROM `cst_questions` "
				+ "WHERE NOT deleted AND class_id=:classId AND subject_id=:subjectId";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(QuestionDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#updateQuestionDetails(com.chalk.salt.common.dto.QuestionDto)
	 */
	@Override
	public String updateQuestionDetails(QuestionDto question) throws Exception {
		final String sqlQuery = "UPDATE `cst_questions` SET `question`=:question, "
				+ "`option1`=:optionA, `option2`=:optionB, `option3`=:optionC, `option4`=:optionD, "
				+ "`answer`=:answer, `modified_at`=:modifiedAt WHERE `question_uuid`=:questionUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("question", question.getQuestion());
            query.addParameter("optionA", question.getOptionA());
            query.addParameter("optionB", question.getOptionB());
            query.addParameter("optionC", question.getOptionC());
            query.addParameter("optionD", question.getOptionD());
            query.addParameter("answer", question.getAnswer());
            query.addParameter("questionUuid", question.getQuestionSecuruuid());   
            query.addParameter("modifiedAt", question.getModifiedDate());
            query.executeUpdate();
            return question.getQuestionSecuruuid();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#deleteQuestion(java.lang.String)
	 */
	@Override
	public Boolean deleteQuestion(String questionSecuruuid) throws Exception {
		final String sqlQuery = "DELETE FROM `cst_questions` WHERE `question_uuid`=:questionUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("questionUuid", questionSecuruuid);   
            query.executeUpdate();
            return true;
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getQuestionIdUsingSecurUuid(java.lang.String)
	 */
	@Override
	public Integer getQuestionIdUsingSecurUuid(String securUuid) throws Exception {
		final String sqlQuery = "SELECT question_id FROM `cst_questions` WHERE secur_uuid=:securUuid";
	
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(Integer.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getPreviousQuestionImage(java.lang.String)
	 */
	@Override
	public String getPreviousQuestionImage(String securUuid) throws Exception {
		final String sqlQuery = "SELECT question_image from cst_questions "
	    		+ " WHERE question_uuid =:securUuid ";
	           
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(String.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#updateQuestionImageDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateQuestionImageDetails(String fileNameToSave, String securUuid) throws Exception {
		final String sqlQuery = "UPDATE cst_questions SET question_image=:fileName "
				+ " WHERE question_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("fileName", fileNameToSave);          
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getVediosListByClassAndSubjectId(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DashBoardVediosContentDto> getVediosListByClassAndSubjectId(
			String classId, String subjectId) throws Exception {
		final String sqlQuery = "SELECT video_id AS videoId,"
				+ " video_embedded_link AS videoEmbedLink,"
				+ " video_title AS videoTitle,"
				+ " video_description AS videoDescription,"
				+ " created_time AS createdTime,"
				+ " modified_time AS modifiedTime,"
				+ " video_uuid AS videoUuid,"
				+ " class_id AS classId,"
				+ " subject_id AS subjectId"
				+ " FROM cst_videos"
				+ " WHERE class_id =:classId AND subject_id=:subjectId"
				+ " ORDER BY created_time DESC LIMIT 4";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(DashBoardVediosContentDto.class);
        }
	}     
}
