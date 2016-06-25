package com.chalk.salt.dao.study.material;

import java.util.List;

import com.chalk.salt.common.dto.VideoContentDto;

/**
 * The Interface StudyMaterialDao.
 */
public interface StudyMaterialDao {

	/**
	 * Save video data.
	 *
	 * @param videoContentDetails the video content details
	 * @return the string
	 * @throws Exception the exception
	 */
	String saveVideoData(VideoContentDto videoContentDetails)throws Exception;

	/**
	 * Gets the videos list using ids.
	 *
	 * @param classId the class id
	 * @param subjectId the subject id
	 * @return the videos list using ids
	 * @throws Exception the exception
	 */
	List<VideoContentDto> getVideosListUsingIds(String classId, String subjectId)throws Exception;

	/**
	 * Gets the video content by id.
	 *
	 * @param videoUuid the video uuid
	 * @return the video content by id
	 * @throws Exception the exception
	 */
	VideoContentDto getVideoContentById(String videoUuid)throws Exception;

	/**
	 * Update video content details.
	 *
	 * @param videoContentDetails the video content details
	 * @throws Exception the exception
	 */
	void updateVideoContentDetails(VideoContentDto videoContentDetails)throws Exception;

	/**
	 * Delete video content data.
	 *
	 * @param videoUuid the video uuid
	 * @throws Exception the exception
	 */
	void deleteVideoContentData(String videoUuid)throws Exception;
}
