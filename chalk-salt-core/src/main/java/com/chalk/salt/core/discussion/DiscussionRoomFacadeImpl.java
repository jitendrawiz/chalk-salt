package com.chalk.salt.core.discussion;

import java.util.List;

import javax.inject.Inject;

import com.chalk.salt.common.dto.DiscussionDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager;

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
	public String saveTopic(DiscussionDto discussionDetails) throws DiscussionException {
		return discussionManager.saveTopic(discussionDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopics()
	 */
	@Override
	public List<DiscussionDto> getTopics() throws DiscussionException {
		return discussionManager.getTopics();
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopic(java.lang.String)
	 */
	@Override
	public DiscussionDto getTopic(String securUuid) throws DiscussionException {
		return discussionManager.getTopic(securUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#deleteTopic(java.lang.String)
	 */
	@Override
	public Boolean deleteTopic(String securUuid) throws DiscussionException {
		return discussionManager.deleteTopic(securUuid);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopics(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionDto> getTopics(String classId, String subjectId)
			throws DiscussionException {
		return discussionManager.getTopics(classId,subjectId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#updateTopic(com.chalk.salt.common.dto.DiscussionDto)
	 */
	@Override
	public void updateTopic(DiscussionDto discussionDetails) throws DiscussionException {
		discussionManager.updateTopic(discussionDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopicsCount(java.lang.String)
	 */
	@Override
	public List<TopicStatisticsDto> getTopicsCount(String classId) throws DiscussionException {
		return discussionManager.getTopicsCount(classId);
	}

}
