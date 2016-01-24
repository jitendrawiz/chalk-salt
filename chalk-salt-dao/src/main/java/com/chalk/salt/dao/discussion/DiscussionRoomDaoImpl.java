package com.chalk.salt.dao.discussion;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.DiscussionDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class DiscussionRoomDaoImpl.
 */
public class DiscussionRoomDaoImpl implements DiscussionRoomDao{

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public void saveTopic(DiscussionDto discussionDetails) throws Exception {

		final String sqlQuery = "INSERT into cst_discussion_topics "
				+ " (`class_id`, `subject_id`, `topic_title`, `topic_description`, `created_date`, `secur_uuid`) "
				+ " VALUES(:classId, :subjectId, :topicTitle, :topicDescription, :createdDate, :securUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", discussionDetails.getClassId());
            query.addParameter("subjectId", discussionDetails.getSubjectId());
            query.addParameter("topicTitle", discussionDetails.getTopicTitle());
            query.addParameter("topicDescription", discussionDetails.getTopicDescription());
            query.addParameter("createdDate", discussionDetails.getCreatedDate());
            query.addParameter("securUuid", discussionDetails.getSecurUuid());
            query.executeUpdate();
        }
	}

	@Override
	public List<DiscussionDto> getTopics() throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `topic_title` as topicTitle, "
				+ "`topic_description` as topicDescription, `created_date` as createdDate, `modified_date` as modifiedDate, "
				+ "`secur_uuid` as securUuid FROM `cst_discussion_topics` WHERE 1";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            return query.executeAndFetch(DiscussionDto.class);
        }
	}

	@Override
	public DiscussionDto getTopic(String securUuid) throws Exception {
		final String sqlQuery = "SELECT `class_id` as classId, `subject_id` as subjectId, `topic_title` as topicTitle, "
				+ "`topic_description` as topicDescription, `created_date` as createdDate, `modified_date` as modifiedDate, "
				+ "`secur_uuid` as securUuid FROM `cst_discussion_topics` WHERE secur_uuid=:securUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(DiscussionDto.class);
        }
	}
}
