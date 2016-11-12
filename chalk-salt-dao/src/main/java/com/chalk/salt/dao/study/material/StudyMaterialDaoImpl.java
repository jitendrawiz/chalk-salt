package com.chalk.salt.dao.study.material;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.NotesContentDto;
import com.chalk.salt.common.dto.NotesDto;
import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class StudyMaterialDaoImpl.
 */
public class StudyMaterialDaoImpl implements StudyMaterialDao {

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.study.material.StudyMaterialDao#saveVideoData(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public String saveVideoData(VideoContentDto videoContentDetails)
			throws Exception {
		final String sqlQuery = "INSERT INTO `cst_videos` (`video_embedded_link`, `video_title`, `video_description`, "
				+ "`modified_time`, `class_id`, `subject_id`, `video_uuid`)"
				+ "VALUES(:videoLink, :videoTitle, :videoDesc, :modifiedTime, :classId, :subjectId, :videoUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("videoLink", videoContentDetails.getVideoLink());
            query.addParameter("videoTitle", videoContentDetails.getVideoTitle());
            query.addParameter("videoDesc", videoContentDetails.getVideoDescription());
            query.addParameter("modifiedTime", videoContentDetails.getModifiedTime());
            query.addParameter("classId", videoContentDetails.getClassId());
            query.addParameter("subjectId", videoContentDetails.getSubjectId());
            query.addParameter("videoUuid", videoContentDetails.getVideoUuid());
            query.executeUpdate();
            return videoContentDetails.getVideoUuid();
        }
}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getVideosListUsingIds(java.lang.String, java.lang.String)
	 */
	@Override
	public List<VideoContentDto> getVideosListUsingIds(String classId,
			String subjectId) throws Exception {
		final String sqlQuery = "SELECT video_embedded_link AS videoLink,"
				+ " video_title AS videoTitle, "
				+ " video_description AS videoDescription, "
				+ " DATE_FORMAT(created_time ,'%d-%M-%Y %H:%i:%S')AS createdTime,"
				+ " DATE_FORMAT(modified_time ,'%d-%M-%Y %H:%i:%S')AS modifiedTime, "
				+ " video_uuid AS videoUuid, "
				+ " class_id AS classId, "
				+ " subject_id AS subjectId "
				+ " FROM cst_videos "
				+ " WHERE class_id= :classId AND subject_id= :subjectId  ORDER BY video_id DESC";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(VideoContentDto.class);
        }
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getVideoContentById(java.lang.String)
	 */
	@Override
	public VideoContentDto getVideoContentById(String videoUuid) throws Exception {
		final String sqlQuery = "SELECT video_embedded_link AS videoLink,"
				+ " video_title AS videoTitle,"
				+ " video_description AS videoDescription,"
				+ " DATE_FORMAT(created_time ,'%d-%M-%Y %H:%i:%S')AS createdTime,"
				+ " DATE_FORMAT(modified_time ,'%d-%M-%Y %H:%i:%S')AS modifiedTime,"
				+ " video_uuid AS videoUuid,"
				+ " class_id AS classId, subject_id AS subjectId"
				+ " FROM cst_videos WHERE video_uuid= :videoUuid ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("videoUuid", videoUuid);
            return query.executeAndFetchFirst(VideoContentDto.class);
        }	
    }

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.study.material.StudyMaterialDao#updateVideoContentDetails(com.chalk.salt.common.dto.VideoContentDto)
	 */
	@Override
	public void updateVideoContentDetails(VideoContentDto videoContentDetails)throws Exception {
		final String sqlQuery = "UPDATE `cst_videos` SET video_embedded_link=:videoLink,"
				+ " video_title=:videoTitle,"
				+ " modified_time=:modifiedDate"
				+ " WHERE video_uuid=:videoUuid LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("videoTitle", videoContentDetails.getVideoTitle());
            query.addParameter("videoLink", videoContentDetails.getVideoLink());
            query.addParameter("modifiedDate", videoContentDetails.getModifiedTime());
            query.addParameter("videoUuid", videoContentDetails.getVideoUuid());
            query.executeUpdate();
        }
			
	}

	/* (non-Javadoc)
	 * @see com.chalk.salt.dao.study.material.StudyMaterialDao#deleteVideoContentData(java.lang.String)
	 */
	@Override
	public void deleteVideoContentData(String videoUuid) throws Exception {
		final String sqlQuery = "delete from cst_videos where video_uuid=:videoUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("videoUuid", videoUuid);
            query.executeUpdate();
        }		
	}

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#saveNotes(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public String saveNotes(NotesContentDto notesContentDetails) throws Exception
    {
        final String sqlQuery = "INSERT INTO `cst_notes` (`notes_title`, `notes_file_name`, `modified_date`, "
                + "`class_id`, `subject_id`, `notes_uuid`)"
                + "VALUES(:notesTitle, :notesFileName, :modifiedDate, :classId, :subjectId, :notesUuid)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("notesTitle", notesContentDetails.getNotesTitle());
            query.addParameter("notesFileName", notesContentDetails.getNotesFileName());
            query.addParameter("modifiedDate", notesContentDetails.getModifiedDate());
            query.addParameter("classId", notesContentDetails.getClassId());
            query.addParameter("subjectId", notesContentDetails.getSubjectId());
            query.addParameter("notesUuid", notesContentDetails.getNotesUuid());
            query.executeUpdate();
            return notesContentDetails.getNotesUuid();
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getPathOfNotesUsingNotesUuid(java.lang.String)
     */
    @Override
    public String getPathOfNotesUsingNotesUuid(String notesUuid) throws Exception
    {
        final String sqlQuery = "SELECT CONCAT(cst_class_type.class_name,'$',cst_class_subjects.subject_name) FROM `cst_notes` "
                + " JOIN cst_class_type ON cst_class_type.class_id=cst_notes.class_id "
                + " JOIN cst_class_subjects ON cst_class_subjects.subject_id=cst_notes.subject_id "
                + " WHERE cst_notes.notes_uuid= :notesUuid ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("notesUuid", notesUuid);
            return (String)query.executeScalar();
        }   
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getNotesListUsingIds(java.lang.String, java.lang.String)
     */
    @Override
    public List<NotesContentDto> getNotesListUsingIds(String classId, String subjectId)throws Exception
    {
        final String sqlQuery = "SELECT notes_title AS notesTitle,"
                + " notes_file_name AS notesFileName, "
                + " notes_uuid AS notesUuid "
                + " FROM cst_notes "
                + " WHERE class_id= :classId AND subject_id= :subjectId  ORDER BY created_date DESC";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery); 
            query.addParameter("classId", classId);
            query.addParameter("subjectId", subjectId);
            return query.executeAndFetch(NotesContentDto.class);
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getNotesContentById(java.lang.String)
     */
    @Override
    public NotesContentDto getNotesContentById(String notesUuid) throws Exception
    {
        final String sqlQuery = "SELECT notes_id AS notesId,"
                + " notes_title AS notesTitle,"
                + " notes_file_name AS notesFileName,"
                + " DATE_FORMAT(created_date ,'%d-%M-%Y %H:%i:%S')AS createdDate,"
                + " DATE_FORMAT(modified_date ,'%d-%M-%Y %H:%i:%S')AS modifiedDate,"
                + " notes_uuid AS notesUuid,"
                + " class_id AS classId, subject_id AS subjectId"
                + " FROM cst_notes WHERE notes_uuid= :notesUuid ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("notesUuid", notesUuid);
            return query.executeAndFetchFirst(NotesContentDto.class);
        }   
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#updateVideoContentDetails(com.chalk.salt.common.dto.NotesContentDto)
     */
    @Override
    public void updateNotesContentDetails(NotesContentDto notesContentDetails) throws Exception
    {
        final String sqlQuery = "UPDATE `cst_notes` SET notes_title=:notesTitle,  "               
                + " modified_date=:modifiedDate"
                + " WHERE notes_uuid=:notesUuid LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("notesTitle", notesContentDetails.getNotesTitle());
            query.addParameter("modifiedDate", notesContentDetails.getModifiedDate());            
            query.addParameter("notesUuid", notesContentDetails.getNotesUuid());
            query.executeUpdate();
        }
        
        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getOldFileName(java.lang.String)
     */
    @Override
    public String getOldFileName(String notesUuid) throws Exception
    {
        final String sqlQuery = "SELECT cst_notes.notes_file_name from cst_notes "
                + " WHERE cst_notes.notes_uuid= :notesUuid ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("notesUuid", notesUuid);
            return (String)query.executeScalar();
        }   
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#deleteNotesContentData(java.lang.String)
     */
    @Override
    public void deleteNotesContentData(String notesUuid) throws Exception
    {
        final String sqlQuery = "delete from cst_notes where notes_uuid=:notesUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("notesUuid", notesUuid);
            query.executeUpdate();
        }   
        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#updateFileNameInDB(java.lang.String, java.lang.String)
     */
    @Override
    public void updateFileNameInDB(String notesFileName, String notesUuid) throws Exception
    {
        final String sqlQuery = "UPDATE `cst_notes` SET notes_file_name=:notesFileName  "               
                + " WHERE notes_uuid=:notesUuid LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("notesFileName", notesFileName);
            query.addParameter("notesUuid", notesUuid);
            query.executeUpdate();
        }
        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.study.material.StudyMaterialDao#getNotesListUsingClassIds(java.lang.String)
     */
    @Override
    public List<NotesDto> getNotesListUsingClassIds(String classId) throws Exception
        {
        final String sqlQuery = "SELECT "
                + " notes_uuid as notesUuid,"
                + " notes_file_name as notesFileName,"
                + " notes_file_name as notesTitle,"
                + " class_name as className,"
                + " subject_name as subjectName"
                + " FROM `cst_notes` "
                + " JOIN  `cst_class_type` ON cst_class_type.class_id=  cst_notes.class_id "
                + " JOIN `cst_class_subjects` ON cst_class_subjects.subject_id=cst_notes.subject_id "
                + " WHERE cst_notes.class_id=:classId ORDER BY notes_id ASC LIMIT 3  ";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("classId", classId);
            return query.executeAndFetch(NotesDto.class);
        }   
        }
}
