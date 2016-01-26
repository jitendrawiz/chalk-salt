package com.chalk.salt.dao.dicussion.manager;

import java.util.List;

import com.chalk.salt.common.dto.DiscussionDto;
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
	String saveTopic(DiscussionDto discussionDetails)throws DiscussionException;

	/**
	 * Gets the topics.
	 *
	 * @return the topics
	 * @throws DiscussionException the discussion exception
	 */
	List<DiscussionDto> getTopics()throws DiscussionException;

	/**
	 * Gets the topic.
	 *
	 * @param securUuid the secur uuid
	 * @return the topic
	 * @throws DiscussionException the discussion exception
	 */
	DiscussionDto getTopic(String securUuid)throws DiscussionException;

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
	List<DiscussionDto> getTopics(String classId, String subjectId)throws DiscussionException;

	/**
	 * Update topic.
	 *
	 * @param discussionDetails the discussion details
	 * @throws DiscussionException the discussion exception
	 */
	void updateTopic(DiscussionDto discussionDetails)throws DiscussionException;

}
