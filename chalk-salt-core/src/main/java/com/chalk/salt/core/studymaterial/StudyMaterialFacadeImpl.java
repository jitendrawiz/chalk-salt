package com.chalk.salt.core.studymaterial;

import java.util.List;

import javax.inject.Inject;

import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;
import com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager;

/**
 * The Class StudyMaterialFacadeImpl.
 */
public class StudyMaterialFacadeImpl implements StudyMaterialFacade {

	/** The study material manager. */
	@Inject
	private StudyMaterialManager studyMaterialManager;
	
	
	
	/* (non-Javadoc)
	 * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#saveVedioData(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public String saveVedioData(VideoContentDto videoContentDetails)
			throws StudyMaterialException {
		return studyMaterialManager.saveVideoData(videoContentDetails);
	}



	/* (non-Javadoc)
	 * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#getVideosListUsingIds(java.lang.String, java.lang.String)
	 */
	@Override
	public List<VideoContentDto> getVideosListUsingIds(String classId,
			String subjectId) throws StudyMaterialException {
		return studyMaterialManager.getVideosListUsingIds(classId,subjectId);
	}



	/* (non-Javadoc)
	 * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#getVideoContentById(java.lang.String)
	 */
	@Override
	public VideoContentDto getVideoContentById(String videoUuid)
			throws StudyMaterialException {
		return studyMaterialManager.getVideoContentById(videoUuid);
	}



	/* (non-Javadoc)
	 * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#updateVideoContentDetails(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public void updateVideoContentDetails(VideoContentDto videoContentDetails)
			throws StudyMaterialException {
		 studyMaterialManager.updateVideoContentDetails(videoContentDetails);
		
	}



	/* (non-Javadoc)
	 * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#deleteVideoContentData(java.lang.String)
	 */
	@Override
	public Boolean deleteVideoContentData(String videoUuid)
			throws StudyMaterialException {
		return studyMaterialManager.deleteVideoContentData(videoUuid);
	}

}
