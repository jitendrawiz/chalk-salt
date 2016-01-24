package com.chalk.salt.core.discussion;

import java.util.List;

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
	String saveTopic(final DiscussionDto discussionDetails)throws DiscussionException;

	List<DiscussionDto> getTopics()throws DiscussionException;

	DiscussionDto getTopic(final String securUuid)throws DiscussionException;

}
