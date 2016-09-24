package com.chalk.salt.dao.studymaterial.manager;

import java.util.List;

import com.chalk.salt.common.dto.NotesContentDto;
import com.chalk.salt.common.dto.NotesDto;
import com.chalk.salt.common.dto.NotesFileDto;
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

    /**
     * Save notes.
     *
     * @param notesContentDto the notes content dto
     * @return the string
     * @throws StudyMaterialException the study material exception
     */
    String saveNotes(NotesContentDto notesContentDto)throws StudyMaterialException;

    /**
     * Upload notes file.
     *
     * @param notesUuid the notes uuid
     * @param notesFileData the notes file data
     * @return the string
     * @throws StudyMaterialException the study material exception
     */
    String uploadNotesFile(String notesUuid, NotesFileDto notesFileData)throws StudyMaterialException;

    /**
     * Gets the notes list using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the notes list using ids
     * @throws StudyMaterialException the study material exception
     */
    List<NotesContentDto> getNotesListUsingIds(String classId, String subjectId)throws StudyMaterialException;

    /**
     * Gets the notes content by id.
     *
     * @param notesUuid the notes uuid
     * @return the notes content by id
     * @throws StudyMaterialException the study material exception
     */
    NotesContentDto getNotesContentById(String notesUuid)throws StudyMaterialException;

    
    /**
     * Update notes content details.
     *
     * @param notesContentDetails the notes content details
     * @throws StudyMaterialException the study material exception
     */
    void updateNotesContentDetails(NotesContentDto notesContentDetails)throws StudyMaterialException;

    /**
     * Update andupload notes file.
     *
     * @param notesUuid the notes uuid
     * @param notesFileData the notes file data
     * @return the string
     * @throws StudyMaterialException the study material exception
     */
    String updateAnduploadNotesFile(String notesUuid, NotesFileDto notesFileData)throws StudyMaterialException;

    /**
     * Delete notes content data.
     *
     * @param notesUuid the notes uuid
     * @return the boolean
     * @throws StudyMaterialException the study material exception
     */
    Boolean deleteNotesContentData(String notesUuid)throws StudyMaterialException;

    /**
     * Gets the notes list using class ids.
     *
     * @param classId the class id
     * @return the notes list using class ids
     * @throws StudyMaterialException the study material exception
     */
    List<NotesDto> getNotesListUsingClassIds(String classId)throws StudyMaterialException;

}
