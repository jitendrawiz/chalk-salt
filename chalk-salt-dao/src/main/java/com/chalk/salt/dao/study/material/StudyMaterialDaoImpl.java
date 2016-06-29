package com.chalk.salt.dao.study.material;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.NotesContentDto;
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
}
