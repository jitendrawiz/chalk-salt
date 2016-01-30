package com.chalk.salt.dao.discussion;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;

/**
 * The Interface DiscussionRoomDao.
 */
public interface DiscussionRoomDao {

	/**
	 * Save topic.
	 *
	 * @param discussionDetails the discussion details
	 * @return the boolean
	 * @throws Exception the exception
	 */
	void saveTopic(DiscussionDto discussionDetails) throws Exception;

	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 * @throws Exception the exception
	 */
	List<DiscussionDto> getTopics() throws Exception;

	/**
	 * Gets the topic.
	 *
	 * @param securUuid the secur uuid
	 * @return the topic
	 * @throws Exception the exception
	 */
	DiscussionDto getTopic(String securUuid) throws Exception;

	/**
	 * Delete topic.
	 *
	 * @param securUuid the secur uuid
	 * @throws Exception the exception
	 */
	void deleteTopic(String securUuid)throws Exception;

	/**
	 * Gets the topics.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the topics
	 * @throws Exception the exception
	 */
	List<DiscussionDto> getTopics(String classId, String subjectId)throws Exception;

	/**
	 * Update topic.
	 *
	 * @param discussionDetails the discussion details
	 * @throws Exception the exception
	 */
	void updateTopic(DiscussionDto discussionDetails)throws Exception;

	/**
	 * Gets the topics count.
	 *
	 * @param classId the class id
	 * @return the topics count
	 * @throws Exception the exception
	 */
	List<TopicStatisticsDto> getTopicsCount(String classId)throws Exception;
	
}
