package com.chalk.salt.dao.dicussion.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.DiscussionDto;
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
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#saveTopic(com.chalk.salt.dao.dto.DiscussionDto)
	 */
	@Override
	public String saveTopic(DiscussionDto discussionDetails) throws DiscussionException {
		logger.info("Save discussion room topic.......");
		try {
			final Date date = new Date();
			final String createdDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			final String securUuid = UUID.randomUUID().toString();
			discussionDetails.setSecurUuid(securUuid);
			discussionDetails.setCreatedDate(createdDate);
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
	public List<DiscussionDto> getTopics() throws DiscussionException {
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
	public DiscussionDto getTopic(String securUuid) throws DiscussionException {
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
		logger.info("delete dicussion topic using secur uuid...");
		try{
			discussionDao.deleteTopic(securUuid);
			return true; // need to be discussed how to implement.
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_DELETE_DISCUSSION_TOPIC, "Fail to delete Discussion Topic", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.dicussion.manager.DiscussionRoomManager#getTopics(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DiscussionDto> getTopics(String classId, String subjectId)
			throws DiscussionException {
		logger.info("fetch list of dicussion topics...");
		try{
			return discussionDao.getTopics(classId,subjectId);
		} catch (final Exception exception) {
            throw new DiscussionException(ErrorCode.FAIL_TO_FETCH_DISCUSSION_TOPICS, "Fail to Fetch list of Discussion Topics", exception);
        }
	}

}
