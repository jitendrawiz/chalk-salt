package com.chalk.salt.core.discussion;

import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.dao.dto.DiscussionDto;

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
	Boolean saveTopic(DiscussionDto discussionDetails)throws DiscussionException;

}
