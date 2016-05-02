package com.chalk.salt.dao.dicussion.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.discussion.DiscussionRoomDao;

/**
 * The Class DiscussionRoomManagerImpl.
 */
public class DiscussionRoomManagerImpl implements DiscussionRoomManager {

	/** The discussion dao. */
	@Inject
	private DiscussionRoomDao discussionDao;
	
	/** The logger. */
    @Inject
    @AppLogger
    private Logger logger;
    
    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public String saveTopic(DiscussionTopicDto discussionDetails) throws DiscussionException {
		logger.info("Save discussion room topic.......");
		try {
			final Date date = new Date();
			final String createdDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			final String securUuid = UUID.randomUUID().toString();
			discussionDetails.setSecurUuid(securUuid);
			discussionDetails.setCreatedDate(createdDate);
			discussionDetails.setModifiedDate(createdDate);
			discussionDao.saveTopic(discussionDetails);
			return securUuid;
			
			
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_SAVE_DISCUSSION_TOPIC, "Fail to Save Discussion Topic", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopics()
	 */
	@Override
	public List<DiscussionTopicDto> getTopics() throws DiscussionException {
		logger.info("fetch list of dicussion topics...");
		try{
			return discussionDao.getTopics();
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPICS, "Fail to Fetch list of Discussion Topics", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopic(java.lang.String)
	 */
	@Override
	public DiscussionTopicDto getTopic(String securUuid) throws DiscussionException {
		logger.info("fetch dicussion topic using secur uuid...");
		try{
			return discussionDao.getTopic(securUuid);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPIC, "Fail to Fetch Discussion Topic", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#deleteTopic(java.lang.String)
	 */
	@Override
	public Boolean deleteTopic(String securUuid) throws DiscussionException {
		logger.info("delete dicussion topic and topic comments using secur uuid...");
		try{
			discussionDao.deleteTopicComments(securUuid);
			discussionDao.deleteTopic(securUuid);
			return true; // need to be discussed how to implement.
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_DELETE_DISCUSSION_TOPIC_AND_COMMENTS, "Fail to delete Discussion Topic and comments", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopics(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionTopicDto> getTopics(String classId, String subjectId)
			throws DiscussionException {
		logger.info("fetch list of dicussion topics...");
		try{
			return discussionDao.getTopics(classId,subjectId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPICS, "Fail to Fetch list of Discussion Topics", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#updateTopic(com.chalk.salt.common.dto.DiscussionDto)
	 */
	@Override
	public void updateTopic(DiscussionTopicDto discussionDetails) throws DiscussionException {
		logger.info("Update discussion room topic.......");
		try {
			final Date date = new Date();
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			discussionDetails.setModifiedDate(modifiedDate);
			discussionDao.updateTopic(discussionDetails);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_UPDATE_DISCUSSION_TOPIC, "Fail to update Discussion Topic", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopicsCount(java.lang.String)
	 */
	@Override
	public List<TopicStatisticsDto> getTopicsCount(String classId) throws DiscussionException {
		logger.info("fetch counts of dicussion topics using subjects ...");
		
		try{
			return  discussionDao.getTopicsCount(classId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPIC_COUNT, "Fail to Fetch count of Discussion Topics", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopicDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TopicDetailsDto> getTopicDetails(String classId, String subjectId) throws DiscussionException {
		logger.info("fetch details of dicussion topics using class and subject ...");
		
		try{
			return  discussionDao.getTopicDetails(classId, subjectId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPIC_DETAILS, "Fail to Fetch details of Discussion Topics", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#saveComments(com.chalk.salt.common.dto.DiscussionCommentDto)
	 */
	@Override
	public String saveComments(DiscussionCommentDto discussionComment) throws DiscussionException {
		logger.info("Save discussion comment .......");
		try {
			final Date date = new Date();
			final String createdDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			final String commentUuid = UUID.randomUUID().toString();
			discussionComment.setCommentUuid(commentUuid);
			discussionComment.setCreatedDate(createdDate);
			discussionComment.setModifiedDate(modifiedDate);
			// if we have topic securUuid then we have to make change in code otherwise for topic_id it is ok.
			discussionDao.saveComments(discussionComment);
			return commentUuid;
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_SAVE_DISCUSSION_TOPIC, "Fail to Save Discussion Comment", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopicCommentDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionCommentDto> getTopicCommentDetails(String classId,
			String subjectId, String topicId) throws DiscussionException {
		logger.info("fetch details of dicussion topics comments using class, subject and topic ...");
		try{
			return  discussionDao.getTopicCommentDetails(classId, subjectId,topicId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPIC_COMMENTS_DETAILS, "Fail to Fetch details Discussion Topic comments", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getSingleTopicDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public TopicDetailsDto getSingleTopicDetails(String classId,
			String subjectId, String topicId) throws DiscussionException {
		logger.info("fetch details of dicussion topic using class, subject and topic ...");
		try{
			return  discussionDao.getSingleTopicDetails(classId, subjectId,topicId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPIC_DETAILS, "Fail to Fetch details of Discussion Topic", exception);
        }
		}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getSubjectName(java.lang.String, java.lang.String)
	 */
	@Override
	public String getSubjectName(String classId, String subjectId)
			throws DiscussionException {
		logger.info("fetch details of dicussion subject");
		try{
			return  discussionDao.getSubjectName(classId, subjectId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_SUBJECT_DETAIL, "Fail to Fetch details of Subject", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopicCommentInfo(java.lang.String)
	 */
	@Override
	public DiscussionCommentDto getTopicCommentInfo(String commentUuid)
			throws DiscussionException {
		logger.info("fetch details of comment of discussion topic");
		try{
			return  discussionDao.getTopicCommentInfo(commentUuid);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_COMMENT_DETAIL, "Fail to Fetch details of topic's comment", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#updateComment(com.chalk.salt.common.dto.DiscussionCommentDto)
	 */
	@Override
	public String updateComment(DiscussionCommentDto discussionComment) throws DiscussionException {
		logger.info("Update discussion comment .......");
		try {
			final Date date = new Date();
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			discussionComment.setModifiedDate(modifiedDate);
			String commentUuid = discussionDao.updateComment(discussionComment);
			return commentUuid;
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_UPDATE_DISCUSSION_COMMENT, "Fail to Update Discussion Topic Comment", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#deleteComment(java.lang.String)
	 */
	@Override
	public Boolean deleteComment(String commentUuid) throws DiscussionException {
		logger.info("delete dicussion comment using comment uuid...");
		try{
			discussionDao.deleteComment(commentUuid);
			return true; // need to be discussed how to implement.
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_DELETE_DISCUSSION_COMMENT, "Fail to delete Discussion Comment", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopicRequests()
	 */
	@Override
	public List<DiscussionTopicRequestDto> getTopicRequests() throws DiscussionException {
		logger.info("Fetch topic requests list .......");
		List<DiscussionTopicRequestDto> topicRequests;
		try {
			topicRequests = discussionDao.getTopicRequests();
			return topicRequests;
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_TOPIC_REQUESTS, "Fail to fetch topic requests", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#approveTopicRequests(String)
	 */
	@Override
	public void approveTopicRequests(String requestSecurUuid) throws DiscussionException {
		logger.info("approving topic request...");
		try{
			discussionDao.approveTopicRequests(requestSecurUuid);
			DiscussionTopicRequestDto topicRequestData=discussionDao.getTopicRequestedData(requestSecurUuid);
			if(topicRequestData.getApproved()){
				final Date date = new Date();
				final String createdDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);			
				discussionDao.saveNewTopicRequested(topicRequestData,createdDate);
			}
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_APPROVE_TOPIC_REQUESTS, "Fail to approve topic requests", exception);
        }
		
	}

}
