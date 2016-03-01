package com.chalk.salt.core.discussion;

import java.util.List;

import javax.inject.Inject;

import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
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
	public String saveTopic(DiscussionTopicDto discussionDetails) throws DiscussionException {
		return discussionManager.saveTopic(discussionDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopics()
	 */
	@Override
	public List<DiscussionTopicDto> getTopics() throws DiscussionException {
		return discussionManager.getTopics();
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopic(java.lang.String)
	 */
	@Override
	public DiscussionTopicDto getTopic(String securUuid) throws DiscussionException {
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
	public List<DiscussionTopicDto> getTopics(String classId, String subjectId)
			throws DiscussionException {
		return discussionManager.getTopics(classId,subjectId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#updateTopic(com.chalk.salt.common.dto.DiscussionDto)
	 */
	@Override
	public void updateTopic(DiscussionTopicDto discussionDetails) throws DiscussionException {
		discussionManager.updateTopic(discussionDetails);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopicsCount(java.lang.String)
	 */
	@Override
	public List<TopicStatisticsDto> getTopicsCount(String classId) throws DiscussionException {
		return discussionManager.getTopicsCount(classId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopicDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TopicDetailsDto> getTopicDetails(String classId, String subjectId) throws DiscussionException {
		return discussionManager.getTopicDetails(classId, subjectId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#saveComments(com.chalk.salt.common.dto.DiscussionCommentDto)
	 */
	@Override
	public String saveComments(DiscussionCommentDto discussionComment) throws DiscussionException {
		return discussionManager.saveComments(discussionComment);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopicCommentDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionCommentDto> getTopicCommentDetails(String classId,
			String subjectId, String topicId) throws DiscussionException {
		return discussionManager.getTopicCommentDetails(classId, subjectId,topicId);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getSingleTopicDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TopicDetailsDto getSingleTopicDetails(String classId,
			String subjectId, String topicId) throws DiscussionException {
		return discussionManager.getSingleTopicDetails(classId, subjectId,topicId);

	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getSubjectName(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSubjectName(String classId, String subjectId)
			throws DiscussionException {
		return discussionManager.getSubjectName(classId, subjectId);

	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#getTopicCommentInfo(java.lang.String)
	 */
	@Override
	public DiscussionCommentDto getTopicCommentInfo(String commentUuid)
			throws DiscussionException {
		return discussionManager.getTopicCommentInfo(commentUuid);

	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#updateComment(com.chalk.salt.common.dto.DiscussionCommentDto)
	 */
	@Override
	public String updateComment(DiscussionCommentDto discussionComment) throws DiscussionException {
		return discussionManager.updateComment(discussionComment);
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.core.discussion.DiscussionRoomFacade#deleteComment(java.lang.String)
	 */
	@Override
	public Boolean deleteComment(String commentUuid) throws DiscussionException {
		return discussionManager.deleteComment(commentUuid);
	}

	@Override
	public List<DiscussionTopicRequestDto> getTopicRequests() throws DiscussionException {
		return discussionManager.getTopicRequests();
	}

}
