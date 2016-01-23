package com.chalk.salt.dao.discussion;

import com.chalk.salt.dao.dto.DiscussionDto;

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
	Boolean saveTopic(DiscussionDto discussionDetails) throws Exception;
	
}
