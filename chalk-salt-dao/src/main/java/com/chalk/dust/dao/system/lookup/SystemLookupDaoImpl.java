/*
 * 
 */
package com.chalk.dust.dao.system.lookup;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemLookupDaoImpl.
 */
public class SystemLookupDaoImpl implements SystemLookupDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chalk.dust.dao.system.lookup.SystemLookupDao#getUserClassesList()
	 */
	@Override
	public List<UserClassDto> getUserClassesList() throws Exception {
		final String sqlQuery = "SELECT class_id,class_name FROM cst_class_type ORDER BY class_id";
		final Sql2o dataSource = ConnectionFactory
				.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
		try (final Connection connection = dataSource.open()) {
			final Query query = connection.createQuery(sqlQuery);
			query.addColumnMapping("class_id", "classId");
			query.addColumnMapping("class_name", "className");
			return query.executeAndFetch(UserClassDto.class);
		}
	}

	/* (non-Javadoc)
	 * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#getSubjectsListByClassId(java.lang.String)
	 */
	@Override
	public List<SubjectDto> getSubjectsListByClassId(String classId)
			throws Exception {
		final String sqlQuery = "SELECT cst_class_subjects.subject_id, cst_class_subjects.subject_name  "
				+ " FROM `cst_class_subjects` "
				+ " JOIN `cst_class_subject_mapping` ON `cst_class_subject_mapping`.subject_id=cst_class_subjects.subject_id "
				+ " JOIN `cst_class_type` ON cst_class_type.class_id=cst_class_subject_mapping.class_id "
				+ " WHERE cst_class_subject_mapping.class_id=:classId";
		final Sql2o dataSource = ConnectionFactory
				.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
		try (final Connection connection = dataSource.open()) {
			final Query query = connection.createQuery(sqlQuery);
			query.addColumnMapping("subject_id", "subjectId");
			query.addColumnMapping("subject_name", "subjectName");
			query.addParameter("classId", classId);
			return query.executeAndFetch(SubjectDto.class);
		}	}

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#getSystemSettings(java.lang.String)
     */
    @Override
    public String getSystemSettings(String settingsKey) throws Exception
    {
        final String sqlQuery = "SELECT `settings_value` AS settingsValue "
                + "FROM `cst_system_settings` AS systemSettings WHERE systemSettings.settings_key=:settingsKey";
    
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("settingsKey", settingsKey);
            return query.executeAndFetchFirst(String.class);
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#getStudentsListByClassId(java.lang.String)
     */
    @Override
    public List<StudentsDto> getStudentsListByClassId(String classId) throws Exception
        {
        final String sqlQuery = " SELECT cst_users.user_id AS studentId, "
                + " CONCAT_WS(' ',first_name,last_name) AS studentName "
                + " FROM `cst_users` "
                + " JOIN cst_logins ON cst_logins.user_id=cst_users.user_id "
                + " AND cst_logins.active WHERE class_id=:classId ORDER BY studentName";
        final Sql2o dataSource = ConnectionFactory
                .provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("classId", classId);
            return query.executeAndFetch(StudentsDto.class);
        }
        }

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#saveNotification(com.chalk.salt.common.dto.NotificationDto)
     */
    @Override
    public Long saveNotification(NotificationDto notification) throws Exception
        {
        final String sqlQuery = "INSERT INTO `cst_notifications` (start_date,end_date,notification,notification_uuid) "
                + "  VALUES  (:startDate,:endDate, :notification, :notificationUuid) ";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("startDate", notification.getStartDate());
            query.addParameter("endDate", notification.getEndDate());
            query.addParameter("notification", notification.getNotification());
            query.addParameter("notificationUuid", notification.getNotificationUuid());
             return (Long)query.executeUpdate().getKey();
            }
        }

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#getStudentNotificationList()
     */
    @Override
    public List<NotificationDto> getStudentNotificationList() throws Exception
        {
        final String sqlQuery = "SELECT  `start_date` AS startDate,  `end_date` AS endDate,   `notification`, "
                + "   `notification_uuid` AS notificationUuid,   `created_date` as createdDate   FROM    cst_notifications"
                + " WHERE CURDATE() BETWEEN start_date AND end_date";
        final Sql2o dataSource = ConnectionFactory
                .provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            return query.executeAndFetch(NotificationDto.class);
        }
        }

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#saveTestGroup(com.chalk.salt.common.dto.TestGroupDto)
     */
    @Override
    public Long saveTestGroup(TestGroupDto testGroupDto) throws Exception
        {
        final String sqlQuery = "INSERT INTO cst_test_group(`test_group_name`,`test_group_uuid`)"
                + " VALUES  (    :testGroupName, :testGroupUuid     ); ";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("testGroupName", testGroupDto.getTestGroupName());
            query.addParameter("testGroupUuid", testGroupDto.getTestGroupUuid());
             return (Long)query.executeUpdate().getKey();
            }
       
        }

    /* (non-Javadoc)
     * @see com.chalk.dust.dao.system.lookup.SystemLookupDao#getTestGroupList()
     */
    @Override
    public List<TestGroupDto> getTestGroupList() throws Exception
        {
        final String sqlQuery = "SELECT  `test_group_name` AS testGroupName, `test_group_uuid`AS testGroupUuid "
                + "   FROM    cst_test_group ";
        final Sql2o dataSource = ConnectionFactory
                .provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            return query.executeAndFetch(TestGroupDto.class);
        } 
        
        }

}
