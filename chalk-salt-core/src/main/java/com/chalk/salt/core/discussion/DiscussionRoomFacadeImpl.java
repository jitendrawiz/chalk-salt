package com.chalk.salt.core.discussion;

import javax.inject.Inject;

import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager;
import com.chalk.salt.dao.dto.DiscussionDto;

/**
 * The Class DiscussionRoomFacadeImpl.
 */
public class DiscussionRoomFacadeImpl implements DiscussionRoomFacade{

	/** The discussion manager. */
	@Inject
	private DiscussionRoomManager discussionManager;
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public Boolean saveTopic(DiscussionDto discussionDetails) throws DiscussionException {
		return discussionManager.saveTopic(discussionDetails);
	}

}
