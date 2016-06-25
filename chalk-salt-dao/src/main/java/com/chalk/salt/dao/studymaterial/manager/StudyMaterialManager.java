package com.chalk.salt.dao.studymaterial.manager;

import java.util.List;

import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;

/**
 * The Interface StudyMaterialManager.
 */
public interface StudyMaterialManager {

	/**
	 * Save video data.
	 *
	 * @param videoContentDetails the video content details
	 * @return the string
	 * @throws StudyMaterialException the study material exception
	 */
	String saveVideoData(VideoContentDto videoContentDetails)throws StudyMaterialException;

	/**
	 * Gets the videos list using ids.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the videos list using ids
	 * @throws StudyMaterialException the study material exception
	 */
	List<VideoContentDto> getVideosListUsingIds(String classId, String subjectId)throws StudyMaterialException;

	/**
	 * Gets the video content by id.
	 *
	 * @param videoUuid the video uuid
	 * @return the video content by id
	 * @throws StudyMaterialException the study material exception
	 */
	VideoContentDto getVideoContentById(String videoUuid)throws StudyMaterialException;

	/**
	 * Update video content details.
	 *
	 * @param videoContentDetails the video content details
	 * @throws StudyMaterialException the study material exception
	 */
	void updateVideoContentDetails(VideoContentDto videoContentDetails)throws StudyMaterialException;

	/**
	 * Delete video content data.
	 *
	 * @param videoUuid the video uuid
	 * @return the boolean
	 * @throws StudyMaterialException the study material exception
	 */
	Boolean deleteVideoContentData(String videoUuid)throws StudyMaterialException;

}
