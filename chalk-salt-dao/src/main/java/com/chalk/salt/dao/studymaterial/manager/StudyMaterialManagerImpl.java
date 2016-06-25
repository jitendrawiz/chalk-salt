package com.chalk.salt.dao.studymaterial.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.dao.study.material.StudyMaterialDao;

/**
 * The Class StudyMaterialManagerImpl.
 */
public class StudyMaterialManagerImpl implements StudyMaterialManager {

	/** The study material dao. */
	@Inject
	private StudyMaterialDao studyMaterialDao;
	
	/** The logger. */
    @Inject
    @AppLogger
    private Logger logger;
    
    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#saveVideoData(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public String saveVideoData(VideoContentDto videoContentDetails)
			throws StudyMaterialException {
		logger.info("Saving video data ...");
		try{
			videoContentDetails.setVideoUuid(UUID.randomUUID().toString());
			final Date date = new Date();
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			videoContentDetails.setModifiedTime(modifiedDate);
			return studyMaterialDao.saveVideoData(videoContentDetails);
		} catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_SAVE_STUDY_MATERIAL, "Fail to save video data", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#getVideosListUsingIds(java.lang.String, java.lang.String)
	 */
	@Override
	public List<VideoContentDto> getVideosListUsingIds(String classId,
			String subjectId) throws StudyMaterialException {
		logger.info("fetch list of videos...");
		try{
			return studyMaterialDao.getVideosListUsingIds(classId,subjectId);
		} catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_FETCH_STUDY_MATERIAL, "Fail to Fetch list of Videos", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#getVideoContentById(java.lang.String)
	 */
	@Override
	public VideoContentDto getVideoContentById(String videoUuid)
			throws StudyMaterialException {
		logger.info("fetch video content using videoUuid..");
		try{
			return studyMaterialDao.getVideoContentById(videoUuid);
		} catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_FETCH_STUDY_MATERIAL, "Fail to Fetch video content", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#updateVideoContentDetails(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public void updateVideoContentDetails(VideoContentDto videoContentDetails)
			throws StudyMaterialException {
		logger.info("Update video content details....");
		try {
			final Date date = new Date();
			final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			videoContentDetails.setModifiedTime(modifiedDate);
			studyMaterialDao.updateVideoContentDetails(videoContentDetails);
		} catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_UPDATE_STUDY_MATERIAL, "Fail to update Study Material", exception);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#deleteVideoContentData(java.lang.String)
	 */
	@Override
	public Boolean deleteVideoContentData(String videoUuid)
			throws StudyMaterialException {
		logger.info("delete video content details...");
		try{
			studyMaterialDao.deleteVideoContentData(videoUuid);
			return true; // need to be discussed how to implement.
		} catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_DELETE_STUDY_MATERIAL, "Fail to delete Discussion Topic and comments", exception);
        }
	}
    
	
}
