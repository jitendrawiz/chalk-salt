package com.chalk.salt.core.discussion;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.common.exceptions.DiscussionException;

/**
 * The Interface DiscussionRoomFacade.
 */
public interface DiscussionRoomFacade {

	/**
	 * Save topic.
	 *
	 * @param discussionDetails the discussion details
	 * @return the boolean
	 * @throws DiscussionException the discussion exception
	 */
	String saveTopic(final DiscussionTopicDto discussionDetails)throws DiscussionException;

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
	DiscussionTopicDto getTopic(final String securUuid)throws DiscussionException;

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
	 * @param discussionDetails the discussion details
	 * @return the string
	 * @throws DiscussionException the discussion exception
	 */
	String saveComments(DiscussionCommentDto discussionComment)throws DiscussionException;

}
