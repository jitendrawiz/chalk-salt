package com.chalk.salt.dao.exam;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.AnswerDto;
import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.DashBoardNotesDto;
import com.chalk.salt.common.dto.DashBoardVediosContentDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionOptionsDto;
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
				+ "`question_uuid`,question_type)"
				+ "VALUES(:classId, :subjectId, :question, :questionUuid,:questionType)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", questionDetails.getClassId());
            query.addParameter("subjectId", questionDetails.getSubjectId());
            query.addParameter("question", questionDetails.getQuestion());
            query.addParameter("questionUuid", questionDetails.getQuestionSecuruuid());  
            query.addParameter("questionType", questionDetails.getQuestionType());          
            return String.valueOf(query.executeUpdate().getKey());
           
        }
	}
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#saveQuestionOptions(com.chalk.salt.common.dto.QuestionOptionsDto)
	 */
	@Override
	public void saveQuestionOptions(QuestionOptionsDto options) throws Exception {
		final String sqlQuery = "INSERT INTO `cst_questions_options` (`question_id`, `name`, `isAnswer`, "
				+ "`answer`)"
				+ "VALUES(:questionId, :name, :isAnswer, :answer)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("questionId", options.getQuestionId());
            query.addParameter("name", options.getName());
            query.addParameter("isAnswer", options.getIsAnswer());
            query.addParameter("answer", options.getAnswer());
            query.executeUpdate();
	}    
	}
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getQuestions(java.lang.String, java.lang.String)
	 */
	@Override
	public List<QuestionDto> getQuestions(String classId, String subjectId) throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, question, "
				+ "`created_at` as creationDate, `modified_at` as modifiedDate, "
				+ "`question_uuid` as questionSecuruuid, question_id as questionId,question_type as questionType FROM `cst_questions` "
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
		final String sqlQuery = "UPDATE `cst_questions` SET `question`=:question,question_type=:questionType, "				
				+ " `modified_at`=:modifiedAt WHERE `question_uuid`=:questionUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("question", question.getQuestion());           
            query.addParameter("questionUuid", question.getQuestionSecuruuid());   
            query.addParameter("modifiedAt", question.getModifiedDate());
            query.addParameter("questionType", question.getQuestionType());
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
	public String getQuestionSecurUuidUsingId(Integer id) throws Exception {
		final String sqlQuery = "SELECT question_uuid FROM `cst_questions` WHERE question_id=:id";
	
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("id", id);
            return query.executeAndFetchFirst(String.class);
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

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.exam.ExamDao#getNotesListByClassAndSubjectId(java.lang.String, java.lang.String)
     */
    @Override
    public List<DashBoardNotesDto> getNotesListByClassAndSubjectId(String classId, String subjectId) throws Exception
    {
        final String sqlQuery = "SELECT notes_id AS notesId,"
                + " notes_title AS notesTitle,"
                + " notes_file_name AS notesFileName,"
                + " DATE_FORMAT(created_date ,'%d-%M-%Y %H:%i:%S')AS createdDate,"
                + " DATE_FORMAT(modified_date ,'%d-%M-%Y %H:%i:%S')AS modifiedDate,"
                + " notes_uuid AS notesUuid,"
                + " class_id AS classId, subject_id AS subjectId"
                + " FROM cst_notes "
                + " WHERE class_id= :classId AND subject_id= :subjectId  ORDER BY created_date DESC LIMIT 4";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(DashBoardNotesDto.class);
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.exam.ExamDao#getQuestionsUsingType(java.lang.String, java.lang.String, int)
     */
    @Override
    public List<QuestionDto> getQuestionsUsingType(String classId, String subjectId, int limitOfQuestions) throws Exception {
        final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `question`, `created_at` as creationDate, `modified_at` as modifiedDate, "
                + "`question_uuid` as questionSecuruuid,question_id as questionId FROM `cst_questions` "
                + "WHERE NOT deleted AND class_id=:classId AND subject_id=:subjectId AND question_type='Practice Question' ORDER BY created_at DESC LIMIT "+ limitOfQuestions;
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(QuestionDto.class);
        }
    }

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getQuestionOptionsUsingQuestionId(java.lang.Integer)
	 */
	@Override
	public List<QuestionOptionsDto> getQuestionOptionsUsingQuestionId(
			Integer questionId) throws Exception {
		final String sqlQuery = "SELECT options_id AS Id,question_id AS QuestionId,"
				+ " cst_questions_options.name AS Name,isAnswer AS IsAnswer,answer AS answer "
				+ " FROM cst_questions_options WHERE cst_questions_options.question_id=:questionId";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("questionId", questionId);
            return query.executeAndFetch(QuestionOptionsDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getQuestionIdUsingSecurUuid(java.lang.String)
	 */
	@Override
	public String getQuestionIdUsingSecurUuid(String questionSecuruuid)
			throws Exception {
		final String sqlQuery = "SELECT question_id FROM `cst_questions` WHERE question_uuid=:questionSecuruuid";
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("questionSecuruuid", questionSecuruuid);
            return query.executeAndFetchFirst(String.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#deleteQuestionOptions(java.lang.String)
	 */
	@Override
	public void deleteQuestionOptions(String questionId) throws Exception {
		final String sqlQuery = "DELETE FROM `cst_questions_options` WHERE `question_id`=:questionId";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("questionId", questionId);   
            query.executeUpdate();
        }		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#saveAnswerDetails(com.chalk.salt.common.dto.AnswerDto, java.lang.String)
	 */
	@Override
	public void saveAnswerDetails(AnswerDto answer, String testId)throws Exception {
		final String sqlQuery = "INSERT INTO `cst_student_test_answers` (`cst_student_test_id`, `question_id`, `question_option_selected_id` )"
				+ "VALUES(:studentTestId, :questionId, :selectedOptionId)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("studentTestId", testId);   
            query.addParameter("questionId", answer.getQuestionId());   
            query.addParameter("selectedOptionId", answer.getAnswered());   
            query.executeUpdate();
	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#saveAnswerTestRecord(com.chalk.salt.common.dto.AnswersDto)
	 */
	@Override
	public String saveAnswerTestRecord(AnswersDto answers)throws Exception {
		final String sqlQuery = "INSERT INTO `cst_student_test` (`student_id`, `class_id`, `subject_id`, "
				+ " test_type_id)"
				+ "VALUES(:studentId, :classId, :subjectId, :testTypeId)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("studentId", answers.getStudentId());   
            query.addParameter("classId", answers.getClassId());   
            query.addParameter("subjectId", answers.getSubjectId());   
            query.addParameter("testTypeId", answers.getTestTypeId());   
            return  String.valueOf(query.executeUpdate().getKey());
        }
	}

 
}
