package com.chalk.salt.dao.exam;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

public class ExamDaoImpl implements ExamDao {

	@Override
	public String saveQuestion(QuestionDto questionDetails) throws Exception {
		final String sqlQuery = "INSERT INTO `chalkndust`.`cst_questions` (`question_id`, `class_id`, `subject_id`, `question`, "
				+ "`option1`, `option2`, `option3`, `option4`, `answer`, `marks`, `deleted`, `modified_at`, `question_uuid`)"
				+ "VALUES(:classId, :subjectId, :question, :option1, :option2, :option3, :option4, :answer, :questionUuid);";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", questionDetails.getClassId());
            query.addParameter("subjectId", questionDetails.getSubjectId());
            query.addParameter("question", questionDetails.getQuestionId());
            query.addParameter("optionA", questionDetails.getOptionA());
            query.addParameter("optionA", questionDetails.getOptionB());
            query.addParameter("optionA", questionDetails.getOptionC());
            query.addParameter("optionA", questionDetails.getOptionD());
            query.addParameter("answer", questionDetails.getAnswer());
            query.addParameter("questionUuid", questionDetails.getQuestionSecuruuid());            
            query.executeUpdate();
            return questionDetails.getQuestionSecuruuid();
        }
	}

}
