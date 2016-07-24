package com.chalk.salt.dao.exam;

import java.util.Date;
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
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;
import com.chalk.salt.common.exceptions.UserException;
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
     * @see com.chalk.salt.dao.exam.ExamDao#getQuestionsUsingType(java.lang.String, java.lang.String, int, java.lang.String)
     */
    @Override
    public List<QuestionDto> getQuestionsUsingType(String classId, String subjectId, int limitOfQuestions,String typeOfQuestion) throws Exception {
        final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `question`, `created_at` as creationDate, `modified_at` as modifiedDate, "
                + "`question_uuid` as questionSecuruuid,question_id as questionId FROM `cst_questions` "
                + " WHERE NOT deleted AND class_id=:classId AND subject_id=:subjectId "
                + " AND question_type=\""+typeOfQuestion+"\" ORDER BY created_at DESC LIMIT "+ limitOfQuestions;
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
				+ " test_type_id,scheduled_test_uuid)"
				+ "VALUES(:studentId, :classId, :subjectId, :testTypeId,:scheduleTestUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("studentId", answers.getStudentId());   
            query.addParameter("classId", answers.getClassId());   
            query.addParameter("subjectId", answers.getSubjectId());   
            query.addParameter("testTypeId", answers.getTestTypeId());
            query.addParameter("scheduleTestUuid", answers.getScheduleTestUuid());
            return  String.valueOf(query.executeUpdate().getKey());
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getTestTypeList()
	 */
	@Override
	public List<TestTypeDto> getTestTypeList() throws Exception {
		final String sqlQuery = "SELECT test_type_uuid AS testTypeUuid,"
				+ " test_duration AS testDuration, "
				+ " no_of_questions AS noOfQuestions,"
				+ " test_type_id AS testTypeId "
				+ " FROM cst_test_type ORDER BY cst_test_type.test_type_id ASC";
		final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
    	try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            return query.executeAndFetch(TestTypeDto.class);
    	}
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#saveScheduleTestData(com.chalk.salt.common.dto.ScheduleTestDto, java.util.Date)
	 */
	@Override
	public String saveScheduleTestData(ScheduleTestDto scheduleTestDetails,
			Date date) throws UserException {
		final String sqlQuery = "INSERT INTO `cst_schedule_test_master` (`test_title`, `test_date`, `test_time`, "
				+ " test_type_uuid,class_id,subject_id,test_uuid)"
				+ "VALUES(:testTitle, :testDate, :testTime, :testTypeUuid, :classId, :subjectId, :testUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("testTitle", scheduleTestDetails.getTestTitle());   
            query.addParameter("testDate", scheduleTestDetails.getTestDate());   
            query.addParameter("testTime", scheduleTestDetails.getTestTime());   
            query.addParameter("testTypeUuid", scheduleTestDetails.getTestTypeUuid());   
            query.addParameter("classId", scheduleTestDetails.getClassId());   
            query.addParameter("subjectId", scheduleTestDetails.getSubjectId());   
            query.addParameter("testUuid", scheduleTestDetails.getScheduleTestUuid());   
            query.executeUpdate();
            return scheduleTestDetails.getScheduleTestUuid();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getScheduleTestsListUsingIds(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ScheduleTestDto> getScheduleTestsListUsingIds(String classId,
			String subjectId) throws Exception {
		final String sqlQuery = "SELECT class_id AS classId, "
				+ " subject_id AS subjectId,"
				+ " test_date AS testDate,"
				+ " test_time AS testTime,"
				+ " test_title AS testTitle,"
				+ " DATE_FORMAT(created_at ,'%d-%M-%Y %H:%i:%S') AS  scheduleTestCreatedDate,"
				+ " test_type_uuid AS testTypeUuid, "
				+ " test_uuid AS scheduleTestUuid "
				+ " FROM `cst_schedule_test_master` "
				+ " WHERE class_id= :classId AND subject_id= :subjectId and test_date>=CURRENT_DATE() ORDER BY test_id DESC";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(ScheduleTestDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getScheduleTestContentById(java.lang.String)
	 */
	@Override
	public ScheduleTestDto getScheduleTestContentById(String scheduleTestUuid)
			throws Exception {
		final String sqlQuery = "SELECT class_id AS classId, "
				+ " subject_id AS subjectId,"
				+ " test_date AS testDate,"
				+ " test_time AS testTime,"
				+ " test_title AS testTitle,"
				+ " DATE_FORMAT(created_at ,'%d-%M-%Y %H:%i:%S') AS  scheduleTestCreatedDate,"
				+ " test_type_uuid AS testTypeUuid, "
				+ " test_uuid AS scheduleTestUuid "
				+ " FROM `cst_schedule_test_master` "
				+ " WHERE  test_uuid=:scheduleTestUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("scheduleTestUuid", scheduleTestUuid);
            return query.executeAndFetchFirst(ScheduleTestDto.class);
        }	
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#updateScheduleTestContentDetails(com.chalk.salt.common.dto.ScheduleTestDto, java.util.Date)
	 */
	@Override
	public void updateScheduleTestContentDetails(
			ScheduleTestDto scheduleTestContentDetails, Date date)
			throws Exception {
		final String sqlQuery = "UPDATE cst_schedule_test_master "
				+ "SET test_date=:testDate,test_time=:testTime,"
				+ "test_title=:testTitle,update_at=:scheduleTestModifiedDate "
				+ "WHERE test_uuid=:scheduleTestUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("testDate", date);
            query.addParameter("testTime", scheduleTestContentDetails.getTestTime());
            query.addParameter("testTitle", scheduleTestContentDetails.getTestTitle());
            query.addParameter("scheduleTestModifiedDate", scheduleTestContentDetails.getScheduleTestModifiedData());
            query.addParameter("scheduleTestUuid", scheduleTestContentDetails.getScheduleTestUuid());
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#deleteScheduleTestContentData(java.lang.String)
	 */
	@Override
	public void deleteScheduleTestContentData(String scheduleTestUuid)
			throws Exception {
		final String sqlQuery = "delete from cst_schedule_test_master where test_uuid=:scheduleTestUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("scheduleTestUuid", scheduleTestUuid);
            query.executeUpdate();
        }	
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#checkListOfScheduleTest(com.chalk.salt.common.dto.ScheduleTestDto, java.util.Date)
	 */
	@Override
	public List<ScheduleTestDto> checkListOfScheduleTest(
			ScheduleTestDto scheduleTestDetails, Date date,String scheduleTestUuid) throws Exception {
			String appendString="";
			if(scheduleTestUuid!=null){
			appendString=" AND test_uuid!=\""+scheduleTestUuid+"\"";
			}
		final String sqlQuery = "SELECT test_date AS testDate,test_time AS testTime,"
				+ " cst_schedule_test_master.test_type_uuid AS testTypeUuid, "
				+ " test_duration AS testDuration ,test_uuid AS scheduleTestUuid"
				+ " FROM `cst_schedule_test_master` "
				+ " JOIN `cst_test_type` ON cst_test_type.test_type_uuid=cst_schedule_test_master.test_type_uuid "
				+ " WHERE test_date=:date AND class_id=:classId AND subject_id=:subjectId "+appendString;
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("date", date);
            query.addParameter("classId", scheduleTestDetails.getClassId());
            query.addParameter("subjectId", scheduleTestDetails.getSubjectId());
            return query.executeAndFetch(ScheduleTestDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.exam.ExamDao#getTestDurationFromTestTypeUuid(java.lang.String)
	 */
	@Override
	public String getTestDurationFromTestTypeUuid(String testTypeUuid)
			throws Exception {
		final String sqlQuery = "SELECT test_duration FROM cst_test_type WHERE test_type_uuid=:testTypeUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("testTypeUuid", testTypeUuid);
            return query.executeAndFetchFirst(String.class);
        }
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.exam.ExamDao#getScheduleTestsListUsingClassId(java.lang.String, java.lang.String)
     */
    @Override
    public List<ScheduleTestDto> getScheduleTestsListUsingClassId(String classId, String studentId) throws Exception
        {
        final String sqlQuery = "SELECT class_id AS classId, "
                + " subject_id AS subjectId,"
                + " test_date AS testDate,"
                + " test_time AS testTime,"
                + " test_title AS testTitle,"
                + " DATE_FORMAT(created_at ,'%d-%M-%Y %H:%i:%S') AS  scheduleTestCreatedDate,"
                + " test_type_uuid AS testTypeUuid, "
                + " test_uuid AS scheduleTestUuid "
                + " FROM `cst_schedule_test_master` "
                + " WHERE class_id= :classId and test_date=CURRENT_DATE() AND test_time<CURRENT_TIME()   "
                + " AND test_uuid NOT IN (SELECT scheduled_test_uuid FROM `cst_student_test` WHERE student_id=:studentId AND scheduled_test_uuid IS NOT NULL)"
                + " ORDER BY test_id DESC";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("studentId", studentId);
            return query.executeAndFetch(ScheduleTestDto.class);
        }
        }

 
}
