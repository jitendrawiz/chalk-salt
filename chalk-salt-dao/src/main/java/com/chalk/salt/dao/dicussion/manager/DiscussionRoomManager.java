package com.chalk.salt.dao.dicussion.manager;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.common.exceptions.DiscussionException;

/**
 * The Interface DiscussionRoomManager.
 */
public interface DiscussionRoomManager {

	/**
	 * Save topic.
	 *
	 * @param discussionDetails the discussion details
	 * @return the boolean
	 * @throws DiscussionException the discussion exception
	 */
	String saveTopic(DiscussionTopicDto discussionDetails)throws DiscussionException;

	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 * @throws DiscussionException the discussion exception
	 */
	List<DiscussionTopicDto> getTopics()throws DiscussionException;

	/**
	 * Gets the topic.
	 *
	 * @param securUuid the secur uuid
	 * @return the topic
	 * @throws DiscussionException the discussion exception
	 */
	DiscussionTopicDto getTopic(String securUuid)throws DiscussionException;

	/**
	 * Delete topic.
	 *
	 * @param securUuid the secur uuid
	 * @return the boolean
	 * @throws DiscussionException the discussion exception
	 */
	Boolean deleteTopic(String securUuid)throws DiscussionException;

	/**
	 * Gets the topics.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the topics
	 * @throws DiscussionException the discussion exception
	 */
	List<DiscussionTopicDto> getTopics(String classId, String subjectId)throws DiscussionException;

	/**
	 * Update topic.
	 *
	 * @param discussionDetails the discussion details
	 * @throws DiscussionException the discussion exception
	 */
	void updateTopic(DiscussionTopicDto discussionDetails)throws DiscussionException;

	/**
	 * Gets the topics count.
	 *
	 * @param classId the class id
	 * @return the topics count
	 * @throws DiscussionException the discussion exception
	 */
	List<TopicStatisticsDto> getTopicsCount(String classId)throws DiscussionException;

	/**
	 * Gets the topic details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the topic details
	 * @throws DiscussionException the discussion exception
	 */
	List<TopicDetailsDto> getTopicDetails(String classId, String subjectId)throws DiscussionException;

	/**
	 * Save comments.
	 *
	 * @param discussionComment the discussion comment
	 * @return the string
	 * @throws DiscussionException the discussion exception
	 */
	String saveComments(DiscussionCommentDto discussionComment)throws DiscussionException;

	/**
	 * Gets the topic comment details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param topicId the topic id
	 * @return the topic comment details
	 * @throws DiscussionException the discussion exception
	 */
	List<DiscussionCommentDto> getTopicCommentDetails(String classId,
			String subjectId, String topicId)throws DiscussionException;

	/**
	 * Gets the single topic details.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @param topicId the topic id
	 * @return the single topic details
	 * @throws DiscussionException the discussion exception
	 */
	TopicDetailsDto getSingleTopicDetails(String classId, String subjectId,
			String topicId)throws DiscussionException;

	/**
	 * Gets the subject name.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the subject name
	 * @throws DiscussionException the discussion exception
	 */
	String getSubjectName(String classId, String subjectId)throws DiscussionException;

	/**
	 * Gets the topic comment info.
	 *
	 * @param commentUuid the comment uuid
	 * @return the topic comment info
	 * @throws DiscussionException the discussion exception
	 */
	DiscussionCommentDto getTopicCommentInfo(String commentUuid)throws DiscussionException;

}
