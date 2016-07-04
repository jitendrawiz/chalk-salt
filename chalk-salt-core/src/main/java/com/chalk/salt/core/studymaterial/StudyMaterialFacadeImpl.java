package com.chalk.salt.core.studymaterial;

import java.util.List;

import javax.inject.Inject;

import com.chalk.salt.common.dto.NotesContentDto;
import com.chalk.salt.common.dto.NotesFileDto;
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



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#saveNotes(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public String saveNotes(NotesContentDto notesContentDto) throws StudyMaterialException
    {
        return studyMaterialManager.saveNotes(notesContentDto);
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#uploadNotesFile(java.lang.String, com.chalk.salt.common.dto.NotesFileDto)
     */
    @Override
    public String uploadNotesFile(String notesUuid, NotesFileDto notesFileData) throws StudyMaterialException
    {
        return studyMaterialManager.uploadNotesFile(notesUuid,notesFileData);
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#getNotesListUsingIds(java.lang.String, java.lang.String)
     */
    @Override
    public List<NotesContentDto> getNotesListUsingIds(String classId, String subjectId) throws StudyMaterialException
    {
        return studyMaterialManager.getNotesListUsingIds(classId,subjectId);
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#getNotesContentById(java.lang.String)
     */
    @Override
    public NotesContentDto getNotesContentById(String notesUuid) throws StudyMaterialException
    {
        return studyMaterialManager.getNotesContentById(notesUuid);
    }

    
    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#updateNotesContentDetails(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public void updateNotesContentDetails(NotesContentDto notesContentDetails) throws StudyMaterialException
    {
        studyMaterialManager.updateNotesContentDetails(notesContentDetails);
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#updateAnduploadNotesFile(java.lang.String, com.chalk.salt.common.dto.NotesFileDto)
     */
    @Override
    public String updateAnduploadNotesFile(String notesUuid, NotesFileDto notesFileData) throws StudyMaterialException
    {
        return studyMaterialManager.updateAnduploadNotesFile(notesUuid,notesFileData);
    }



    /* (non-Javadoc)
     * @see com.chalk.salt.core.studymaterial.StudyMaterialFacade#deleteNotesContentData(java.lang.String)
     */
    @Override
    public Boolean deleteNotesContentData(String notesUuid) throws StudyMaterialException
    {
        return studyMaterialManager.deleteNotesContentData(notesUuid);
    }

}
