package com.chalk.salt.core.discussion;

import com.chalk.salt.common.dto.DiscussionDto;
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
	String saveTopic(DiscussionDto discussionDetails)throws DiscussionException;

}
