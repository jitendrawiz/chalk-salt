package com.chalk.salt.dao.discussion;

import com.chalk.salt.common.dto.DiscussionDto;

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
	void saveTopic(DiscussionDto discussionDetails) throws Exception;
	
}
