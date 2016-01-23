package com.chalk.salt.dao.discussion;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.dao.dto.DiscussionDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class DiscussionRoomDaoImpl.
 */
public class DiscussionRoomDaoImpl implements DiscussionRoomDao{

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.discussion.DiscussionRoomDao#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public Boolean saveTopic(DiscussionDto discussionDetails) throws Exception {

		final String sqlQuery = "INSERT into cst_discussion_topics "
				+ " (`class_id`, `subject_id`, `topic_title`, `topic_description`, `created_date`) "
				+ " VALUES(:classId, :subjectId, :topicTitle, :topicDescription, :createdDate)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("classId", discussionDetails.getClassId());
            query.addParameter("subjectId", discussionDetails.getSubjectId());
            query.addParameter("topicTitle", discussionDetails.getTopicTitle());
            query.addParameter("topicDescription", discussionDetails.getTopicDescription());
            query.addParameter("createdDate", discussionDetails.getCreatedDate());
            query.executeUpdate();
            return true;
        }
	}
}
