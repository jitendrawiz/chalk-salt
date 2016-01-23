package com.chalk.salt.dao.dicussion.manager;

import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.dao.dto.DiscussionDto;

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
	Boolean saveTopic(DiscussionDto discussionDetails)throws DiscussionException;

}
