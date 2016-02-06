package com.chalk.salt.dao.discussion;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
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
	void saveTopic(DiscussionTopicDto discussionDetails) throws Exception;

	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 * @throws Exception the exception
	 */
	List<DiscussionTopicDto> getTopics() throws Exception;

	/**
	 * Gets the topic.
	 *
	 * @param securUuid the secur uuid
	 * @return the topic
	 * @throws Exception the exception
	 */
	DiscussionTopicDto getTopic(String securUuid) throws Exception;

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
	List<DiscussionTopicDto> getTopics(String classId, String subjectId)throws Exception;

	/**
	 * Update topic.
	 *
	 * @param discussionDetails the discussion details
	 * @throws Exception the exception
	 */
	void updateTopic(DiscussionTopicDto discussionDetails)throws Exception;

	/**
	 * Gets the topics count.
	 *
	 * @param classId the class id
	 * @return the topics count
	 * @throws Exception the exception
	 */
	List<TopicStatisticsDto> getTopicsCount(String classId)throws Exception;

	/**
	 * Gets the topic details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the topic details
	 * @throws Exception the exception
	 */
	List<TopicDetailsDto> getTopicDetails(String classId, String subjectId)throws Exception;

	/**
	 * Save comments.
	 *
	 * @param discussionComment the discussion comment
	 * @throws Exception the exception
	 */
	void saveComments(DiscussionCommentDto discussionComment)throws Exception;

	/**
	 * Gets the topic comment details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param topicId the topic id
	 * @return the topic comment details
	 * @throws Exception the exception
	 */
	List<DiscussionCommentDto> getTopicCommentDetails(String classId,
			String subjectId, String topicId)throws Exception;

	/**
	 * Gets the single topic details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param topicId the topic id
	 * @return the single topic details
	 * @throws Exception the exception
	 */
	TopicDetailsDto getSingleTopicDetails(String classId, String subjectId,
			String topicId)throws Exception;

	/**
	 * Gets the subject name.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the subject name
	 * @throws Exception the exception
	 */
	String getSubjectName(String classId, String subjectId)throws Exception;

	
}
