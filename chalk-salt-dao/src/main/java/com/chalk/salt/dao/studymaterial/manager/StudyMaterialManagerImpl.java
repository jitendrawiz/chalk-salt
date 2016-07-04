package com.chalk.salt.dao.studymaterial.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.dust.dao.system.lookup.SystemLookupDao;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.NotesContentDto;
import com.chalk.salt.common.dto.NotesFileDto;
import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.common.util.SystemSettingsKey;
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
    
    /** The system lookup dao. */
    @Inject
    private SystemLookupDao systemLookupDao;

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
            throw new StudyMaterialException(ErrorCode.FAIL_TO_UPDATE_STUDY_MATERIAL, "Fail to update video content", exception);
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
            throw new StudyMaterialException(ErrorCode.FAIL_TO_DELETE_STUDY_MATERIAL, "Fail to delete video content", exception);
        }
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#saveNotes(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public String saveNotes(NotesContentDto notesContentDetails) throws StudyMaterialException
    {
        logger.info("save notes content details...");
        try{
        notesContentDetails.setNotesUuid(UUID.randomUUID().toString());
        final Date date = new Date();
        final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        notesContentDetails.setModifiedDate(modifiedDate);
        return studyMaterialDao.saveNotes(notesContentDetails);
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_SAVE_STUDY_MATERIAL, "Fail to save notes content", exception);
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#uploadNotesFile(java.lang.String, com.chalk.salt.common.dto.NotesFileDto)
     */
    @Override
    public String uploadNotesFile(String notesUuid, NotesFileDto notesFileData) throws StudyMaterialException
    {
        logger.info("Uploading Notes file");
        try {
            String destPath = systemLookupDao.getSystemSettings(SystemSettingsKey.NOTES_FILE.name());
            String appendedPath=studyMaterialDao.getPathOfNotesUsingNotesUuid(notesUuid);
            String appPath[]=appendedPath.split("\\$");
            destPath += String.join(File.separator, appPath[0],appPath[1],notesUuid);
            File file = new File(destPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = notesFileData.getName();
            destPath = destPath + File.separator + fileName;
            File oldfile = new File(destPath);
            if (oldfile.exists()) {
                oldfile.delete();
            }
            FileInputStream fin = new FileInputStream(
                    notesFileData.getFile());
            FileOutputStream fout = new FileOutputStream(destPath);
            int i = 0;
            while ((i = fin.read()) != -1) {
                fout.write((byte) i);
            }
            fin.close();
            fout.close();
            logger.info("Notes file uploaded successfully");
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_SAVE_NOTES_FILE, "Fail to save notes file", exception);
        }
        return notesUuid;
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#getNotesListUsingIds(java.lang.String, java.lang.String)
     */
    @Override
    public List<NotesContentDto> getNotesListUsingIds(String classId, String subjectId) throws StudyMaterialException
    {
        logger.info("fetch list of notes...");
        try{
            return studyMaterialDao.getNotesListUsingIds(classId,subjectId);
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_FETCH_STUDY_MATERIAL, "Fail to Fetch list of Notes", exception);
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#getNotesContentById(java.lang.String)
     */
    @Override
    public NotesContentDto getNotesContentById(String notesUuid) throws StudyMaterialException
    {
        logger.info("fetch notes content using notesUuid..");
        try{
            return studyMaterialDao.getNotesContentById(notesUuid);
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_FETCH_STUDY_MATERIAL, "Fail to Fetch notes content", exception);
        }
    }

    
    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#updateNotesContentDetails(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public void updateNotesContentDetails(NotesContentDto notesContentDetails) throws StudyMaterialException
    {
        logger.info("Update notes content details....");
        try {
            final Date date = new Date();
            final String modifiedDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            notesContentDetails.setModifiedDate(modifiedDate);
            studyMaterialDao.updateNotesContentDetails(notesContentDetails);
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_UPDATE_STUDY_MATERIAL, "Fail to update notes content", exception);
        }
        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#updateAnduploadNotesFile(java.lang.String, com.chalk.salt.common.dto.NotesFileDto)
     */
    @Override
    public String updateAnduploadNotesFile(String notesUuid, NotesFileDto notesFileData) throws StudyMaterialException
    {
        logger.info("Uploading Notes file");
        try {
            String destPath = systemLookupDao.getSystemSettings(SystemSettingsKey.NOTES_FILE.name());
            String appendedPath=studyMaterialDao.getPathOfNotesUsingNotesUuid(notesUuid);
            String appPath[]=appendedPath.split("\\$");
            destPath += String.join(File.separator, appPath[0],appPath[1],notesUuid);
            //Code to delete old file
            String oldFileName=studyMaterialDao.getOldFileName(notesUuid);
            String oldfilePath=destPath+File.separator+oldFileName;
            File oldfile = new File(oldfilePath);
            if (oldfile.exists()) {
                oldfile.delete();
            }            
            String fileName = notesFileData.getName();
            destPath = destPath + File.separator + fileName;
            File oldfileWithCurrentName = new File(destPath);
            if (oldfileWithCurrentName.exists()) {
                oldfileWithCurrentName.delete();
            }
            FileInputStream fin = new FileInputStream(
                    notesFileData.getFile());
            FileOutputStream fout = new FileOutputStream(destPath);
            int i = 0;
            while ((i = fin.read()) != -1) {
                fout.write((byte) i);
            }
            fin.close();
            fout.close();
            studyMaterialDao.updateFileNameInDB(notesFileData.getName(),notesUuid);
            logger.info("Notes file uploaded successfully");
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_SAVE_NOTES_FILE, "Fail to update notes file", exception);
        }
        return notesUuid;
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.studymaterial.manager.StudyMaterialManager#deleteNotesContentData(java.lang.String)
     */
    @Override
    public Boolean deleteNotesContentData(String notesUuid) throws StudyMaterialException
    {
        logger.info("delete notes content details...");
        try{
            //delete old uploaded file with the old name.
            String destPath = systemLookupDao.getSystemSettings(SystemSettingsKey.NOTES_FILE.name());
            String appendedPath=studyMaterialDao.getPathOfNotesUsingNotesUuid(notesUuid);
            String appPath[]=appendedPath.split("\\$");
            destPath += String.join(File.separator, appPath[0],appPath[1],notesUuid);
            File folderPathToDelete=new File(destPath);
            String oldFileName=studyMaterialDao.getOldFileName(notesUuid);
            String oldfilePath=destPath+File.separator+oldFileName;
            File oldfile = new File(oldfilePath);
            if (oldfile.exists()) {
                oldfile.delete();
            }
            if (folderPathToDelete.exists()) {
            folderPathToDelete.delete();
            }
            studyMaterialDao.deleteNotesContentData(notesUuid);
            return true; 
        } catch (final Exception exception) {
            throw new StudyMaterialException(ErrorCode.FAIL_TO_DELETE_STUDY_MATERIAL, "Fail to delete notes content data and file", exception);
        }
    }
    
	
}
