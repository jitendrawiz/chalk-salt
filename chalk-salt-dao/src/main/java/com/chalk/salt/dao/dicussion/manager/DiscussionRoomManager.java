package com.chalk.salt.dao.dicussion.manager;

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

}
