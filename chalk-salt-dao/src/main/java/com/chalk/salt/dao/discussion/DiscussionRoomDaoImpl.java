package com.chalk.salt.dao.discussion;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class DiscussionRoomDaoImpl.
 */
public class DiscussionRoomDaoImpl implements DiscussionRoomDao{

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public void saveTopic(DiscussionTopicDto discussionDetails) throws Exception {

		final String sqlQuery = "INSERT into cst_discussion_topics "
				+ " (`class_id`, `subject_id`, `topic_title`, `topic_description`, `created_date`, `secur_uuid`,modified_date) "
				+ " VALUES(:classId, :subjectId, :topicTitle, :topicDescription, :createdDate, :securUuid,:modifiedDate)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", discussionDetails.getClassId());
            query.addParameter("subjectId", discussionDetails.getSubjectId());
            query.addParameter("topicTitle", discussionDetails.getTopicTitle());
            query.addParameter("topicDescription", discussionDetails.getTopicDescription());
            query.addParameter("createdDate", discussionDetails.getCreatedDate());
            query.addParameter("securUuid", discussionDetails.getSecurUuid());
            query.addParameter("modifiedDate", discussionDetails.getModifiedDate());
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopics()
	 */
	@Override
	public List<DiscussionTopicDto> getTopics() throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `topic_title` as topicTitle, "
				+ "`topic_description` as topicDescription, `created_date` as createdDate, `modified_date` as modifiedDate, "
				+ "`secur_uuid` as securUuid FROM `cst_discussion_topics`";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            return query.executeAndFetch(DiscussionTopicDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopic(java.lang.String)
	 */
	@Override
	public DiscussionTopicDto getTopic(String securUuid) throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `topic_title` as topicTitle, "
				+ "`topic_description` as topicDescription, `created_date` as createdDate, `modified_date` as modifiedDate, "
				+ "`secur_uuid` as securUuid FROM `cst_discussion_topics` WHERE secur_uuid=:securUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(DiscussionTopicDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#deleteTopic(java.lang.String)
	 */
	@Override
	public void deleteTopic(String securUuid) throws Exception {
		final String sqlQuery = "delete from cst_discussion_topics where secur_uuid=:securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("securUuid", securUuid);
            query.executeUpdate();
            
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopics(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionTopicDto> getTopics(String classId, String subjectId)
			throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `topic_title` as topicTitle, "
				+ "`topic_description` as topicDescription, `created_date` as createdDate, `modified_date` as modifiedDate, "
				+ "`secur_uuid` as securUuid FROM `cst_discussion_topics` where class_id=:classId and subject_id =:subjectId";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(DiscussionTopicDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#updateTopic(com.chalk.salt.common.dto.DiscussionDto)
	 */
	@Override
	public void updateTopic(DiscussionTopicDto discussionDetails) throws Exception {
		final String sqlQuery = "UPDATE cst_discussion_topics "
				+ " set `topic_title`=:topicTitle, "
				+ "`topic_description`=:topicDescription, `modified_date`=:modifiedDate "
				+ " WHERE secur_uuid=:securUuid LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("topicTitle", discussionDetails.getTopicTitle());
            query.addParameter("topicDescription", discussionDetails.getTopicDescription());
            query.addParameter("modifiedDate", discussionDetails.getModifiedDate());
            query.addParameter("securUuid", discussionDetails.getSecurUuid());
            query.executeUpdate();
        }
		
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopicsCount(java.lang.String)
	 */
	@Override
	public List<TopicStatisticsDto> getTopicsCount(String classId) throws Exception {
		final String sqlQuery = "SELECT DISTINCT "
				+ " subjects.subject_id AS subjectId, "
				+ " subjects.subject_name AS subjectName,"
				+ " (SELECT COUNT(cst_discussion_topics.discussion_topic_id) FROM cst_discussion_topics "
				+ " WHERE cst_discussion_topics.class_id=cst_class_subject_mapping.class_id"
				+ "  AND cst_discussion_topics.subject_id=cst_class_subject_mapping.subject_id) AS topics,"
				+ " (SELECT COUNT(cst_discussion_topic_comments.discussion_comment_id) "
				+ " FROM cst_discussion_topic_comments "
				+ " INNER JOIN cst_discussion_topics ON cst_discussion_topic_comments.discussion_topic_id = cst_discussion_topics.discussion_topic_id "
				+ " WHERE cst_discussion_topics.subject_id = cst_class_subject_mapping.subject_id "
				+ " )AS comments, "
				+ " (SELECT "
				+ " MAX(cst_discussion_topic_comments.modified_date)"
				+ " FROM "
				+ " cst_discussion_topics "
				+ " INNER JOIN cst_discussion_topic_comments ON cst_discussion_topics.discussion_topic_id = cst_discussion_topic_comments.discussion_topic_id "
				+ " WHERE cst_discussion_topics.subject_id = subjects.subject_id) AS lastModifiedDate, "
				+ " (SELECT CONCAT(cst_users.first_name,cst_users.middle_name,cst_users.last_name) "
				+ " FROM cst_discussion_topics "
				+ " INNER JOIN cst_users ON cst_users.class_id = cst_discussion_topics.class_id "
				+ " INNER JOIN cst_discussion_topic_comments ON cst_discussion_topics.discussion_topic_id = cst_discussion_topic_comments.discussion_topic_id "
				+ " WHERE cst_discussion_topics.subject_id = subjects.subject_id "
				+ " HAVING MAX(cst_discussion_topic_comments.modified_date) "
				+ " )AS lastModifiedUserName  "
				+ " FROM cst_class_subjects subjects "
				+ " JOIN `cst_class_subject_mapping` ON `cst_class_subject_mapping`.subject_id=subjects.subject_id "
				+ " JOIN cst_discussion_topics topics ON topics.class_id=cst_class_subject_mapping.class_id "
				+ " WHERE  cst_class_subject_mapping.class_id=:classId ";
		
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            return query.executeAndFetch(TopicStatisticsDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopicDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TopicDetailsDto> getTopicDetails(String classId, String subjectId) throws Exception {

		final String sqlQuery = "SELECT DISTINCT topic_title AS topicTitle,discussion_topic_id AS topicId,  "
				+ "(SELECT COUNT(cst_discussion_topic_comments.discussion_comment_id) "
				+ "FROM cst_discussion_topic_comments "
				+ "WHERE cst_discussion_topic_comments.discussion_topic_id = discussion_topics.discussion_topic_id"
				+ ")AS comments,"
				+ "(SELECT MAX(cst_discussion_topic_comments.modified_date) FROM cst_discussion_topic_comments "
				+ "WHERE cst_discussion_topic_comments.discussion_topic_id = discussion_topics.discussion_topic_id) AS lastModifiedDate, "
				+ "(SELECT DISTINCT CONCAT(cst_users.first_name,cst_users.middle_name,cst_users.last_name) "
				+ "FROM cst_discussion_topics INNER JOIN cst_users ON cst_users.class_id = cst_discussion_topics.class_id "
				+ "INNER JOIN cst_discussion_topic_comments ON cst_discussion_topic_comments.discussion_topic_id = cst_discussion_topics.discussion_topic_id "
				+ "WHERE cst_users.class_id = discussion_topics.class_id AND cst_discussion_topics.discussion_topic_id = discussion_topics.discussion_topic_id "
				+ "HAVING MAX(cst_discussion_topic_comments.modified_date))AS lastModifiedUserName "
				+ "FROM cst_discussion_topics AS discussion_topics "
				+ "WHERE  discussion_topics.class_id=:classId AND discussion_topics.subject_id=:subjectId";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(TopicDetailsDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#saveComments(com.chalk.salt.common.dto.DiscussionCommentDto)
	 */
	@Override
	public void saveComments(DiscussionCommentDto discussionComment) throws Exception {
		final String sqlQuery = "INSERT into cst_discussion_topic_comments "
				+ " (`discussion_topic_id`, `general_comments`, `created_date`, `modified_date`, `comment_uuid`,user_securUuid) "
				+ " VALUES(:discussionTopicId, :generalComments, :createdDate, :modifiedDate, :commentUuid, :userSecurUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("discussionTopicId", discussionComment.getDiscussionTopicId());
            query.addParameter("generalComments", discussionComment.getGeneralComments());
            query.addParameter("createdDate", discussionComment.getCreatedDate());
            query.addParameter("modifiedDate", discussionComment.getModifiedDate());
            query.addParameter("commentUuid", discussionComment.getCommentUuid());
            query.addParameter("userSecurUuid", discussionComment.getUserSecurUuid());
            query.executeUpdate();
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getTopicCommentDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionCommentDto> getTopicCommentDetails(String classId,
			String subjectId, String topicId) throws Exception {
		final String sqlQuery = "SELECT DISTINCT "
				+ " cst_discussion_topics.topic_title AS topicTitle,"
				+ " cst_discussion_topics.topic_description AS topicDescription,"
				+ " cst_discussion_topics.class_id AS classId,"
				+ " cst_discussion_topics.subject_id AS subjectId,"
				+ " CONCAT_WS(' ',cst_users.first_name,cst_users.middle_name,cst_users.last_name) AS userName,"
				+ " cst_discussion_topic_comments.discussion_comment_id AS discussionCommentId,"
				+ " cst_discussion_topic_comments.discussion_topic_id AS discussionTopicId,"
				+ " cst_discussion_topic_comments.general_comments AS generalComments,"
				+ " cst_discussion_topic_comments.modified_date AS modifiedDate, "
				+ " cst_discussion_topic_comments.user_securUuid AS userSecurUuid, "
				+ " cst_discussion_topic_comments.comment_uuid  AS commentUuid,"
				+ " cst_users.created_date as joinedDate "
				+ " FROM "
				+ " cst_discussion_topics "
				+ " INNER JOIN cst_users ON cst_discussion_topics.class_id = cst_users.class_id "
				+ " INNER JOIN cst_discussion_topic_comments ON cst_discussion_topic_comments.discussion_topic_id = cst_discussion_topics.discussion_topic_id "
				+ " AND cst_users.secur_uuid = cst_discussion_topic_comments.user_securUuid"
				+ " WHERE cst_discussion_topics.class_id =:classId AND "
				+ " cst_discussion_topics.subject_id =:subjectId AND "
				+ " cst_discussion_topics.discussion_topic_id =:topicId AND "
				+ " cst_discussion_topic_comments.delete_status = 1 "
				+ " GROUP BY cst_discussion_topic_comments.discussion_comment_id";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            query.addParameter("topicId", topicId);
            return query.executeAndFetch(DiscussionCommentDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getSingleTopicDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TopicDetailsDto getSingleTopicDetails(String classId,
			String subjectId, String topicId) throws Exception {
		final String sqlQuery = "SELECT DISTINCT "
				+ " cst_discussion_topics.topic_title AS topicTitle,"
				+ " cst_discussion_topics.topic_description AS topicDescription "
				+ " FROM "
				+ " cst_discussion_topics "
				+ " WHERE "
				+ " cst_discussion_topics.subject_id =:subjectId AND "
				+ " cst_discussion_topics.class_id =:classId AND "
				+ " cst_discussion_topics.discussion_topic_id =:topicId ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            query.addParameter("topicId", topicId);
            return query.executeAndFetchFirst(TopicDetailsDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#getSubjectName(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSubjectName(String classId, String subjectId)
			throws Exception {
		final String sqlQuery = "SELECT DISTINCT "
				+ " cst_class_subjects.subject_name AS subjectName "
				+ " FROM "
				+ " cst_class_subjects "
				+ " INNER JOIN cst_class_subject_mapping ON cst_class_subjects.subject_id = cst_class_subject_mapping.subject_id "
				+ " WHERE  "
				+ " cst_class_subject_mapping.class_id =:classId AND "
				+ " cst_class_subject_mapping.subject_id =:subjectId ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetchFirst(String.class);
        }
	}
}
