package com.chalk.salt.dao.study.material;

import java.util.List;

import com.chalk.salt.common.dto.NotesContentDto;
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

    /**
     * Save notes.
     *
     * @param notesContentDetails the notes content details
     * @return the string
     * @throws Exception the exception
     */
    String saveNotes(NotesContentDto notesContentDetails)throws Exception;

    /**
     * Gets the path of notes using notes uuid.
     *
     * @param notesUuid the notes uuid
     * @return the path of notes using notes uuid
     * @throws Exception the exception
     */
    String getPathOfNotesUsingNotesUuid(String notesUuid)throws Exception;

    /**
     * Gets the notes list using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the notes list using ids
     * @throws Exception the exception
     */
    List<NotesContentDto> getNotesListUsingIds(String classId, String subjectId)throws Exception;

    /**
     * Gets the notes content by id.
     *
     * @param notesUuid the notes uuid
     * @return the notes content by id
     * @throws Exception the exception
     */
    NotesContentDto getNotesContentById(String notesUuid)throws Exception;

    
    /**
     * Update notes content details.
     *
     * @param notesContentDetails the notes content details
     * @throws Exception the exception
     */
    void updateNotesContentDetails(NotesContentDto notesContentDetails)throws Exception;

    /**
     * Gets the old file name.
     *
     * @param notesUuid the notes uuid
     * @return the old file name
     * @throws Exception the exception
     */
    String getOldFileName(String notesUuid)throws Exception;

    /**
     * Delete notes content data.
     *
     * @param notesUuid the notes uuid
     * @throws Exception the exception
     */
    void deleteNotesContentData(String notesUuid)throws Exception;

    /**
     * Update file name in db.
     *
     * @param name the name
     * @param notesUuid the notes uuid
     * @throws Exception the exception
     */
    void updateFileNameInDB(String name, String notesUuid)throws Exception;
}
