/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.dao.user;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;



/**
 * The Class UserDaoImpl.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
public class UserDaoImpl implements UserDao {
    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.dao.office.OfficeDao#fetchUserDetails(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto fetchUserDetails(final String securUuid) throws Exception {
        final String sqlQuery =
            "SELECT `tbl_secur`.`secur_uuid` AS securUuid, `tbl_secur`.`forename` AS forename, `tbl_secur`.`aka` AS displayAs, "
                + " `tbl_secur`.`middle` AS middle, `tbl_secur`.`surname` AS surname, `tbl_secur`.`email` as email, locale FROM tbl_secur AS tbl_secur "
                + "WHERE ((tbl_secur.secur_uuid = :securUuid))";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        UserDto userDetail = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            userDetail = query.executeAndFetchFirst(UserDto.class);
        }
        return userDetail;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String userName) throws Exception {
        final String sqlQuery = "SELECT `cst_logins`.`username` AS userName  "
            + " FROM cst_logins AS cst_logins "
            + "WHERE ((cst_logins.username = :userName))";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userName", userName);
            final Table table = query.executeAndFetchTable();
            final List<Row> rows = table.rows();
            if (rows != null && !rows.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the user id.
     *
     * @param securUuid the secur uuid
     * @return the user id
     * @throws Exception the exception
     */
    @Override
    public Long getUserIdBySecurUuid(final String securUuid) throws Exception {
        Long userId = null;
        final String sqlQuery = "SELECT `tbl_user_domain`.`user_id` AS userId "
            + " FROM tbl_user_domain AS tbl_user_domain "
            + "WHERE ((tbl_user_domain.secur_uuid = :securId)) LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securId", securUuid);
            userId = query.executeScalar(Long.class);
        }
        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#saveUser(java.lang.String, java.lang.String)
     */
    @Override
    public Long saveLoginDetails(final String username, final String hashedPassword) throws Exception {
        final String sqlQuery = "INSERT  INTO cst_logins (`username`,`password`,`active`) "
            + "VALUES(:username, :password, :active)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("username", username);
            query.addParameter("password", hashedPassword);
            query.addParameter("active", 1);
            return (Long) query.executeUpdate().getKey();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#saveUserDetails(java.lang.String, uk.co.propco.common.dto.UserDetail, java.util.List)
     */
    @Override
    public boolean saveUserDetails(final UserDto userDetail) throws Exception {

        final String sqlQuery = "INSERT INTO cst_users(`user_id`, `first_name`, `middle_name`, `last_name`, `contact_id`, `secur_uuid`)"
        		+ "VALUES(:userId, :firstName, :middleName, :lastName, :contactId, :securUuid)";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);

            query.addParameter("securUuid", userDetail.getSecurUuid());
            query.addParameter("firstName", userDetail.getFirstName());
            query.addParameter("middleName", userDetail.getMiddleName());
            query.addParameter("lastName", userDetail.getLastName());
            query.addParameter("userId", userDetail.getUserId());
            query.addParameter("contactId", userDetail.getContactId());

            query.executeUpdate();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#fetchUsers(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto fetchUsers(final String securUuid) throws Exception {
        final String sqlQuery = "SELECT tbl_secur.`secur_uuid` AS securUuid, tbl_secur.`forename`, tbl_secur.`surname`, tbl_secur.`middle`, "
            + " tbl_secur.`jobtitle` AS jobTitle, tbl_secur.`aka` AS displayAs, tbl_offices.name AS officeName FROM `tbl_secur` AS tbl_secur "
            + " LEFT JOIN `tbl_offices` AS tbl_offices ON tbl_offices.`offices_id` = tbl_secur.`office`"
            + " WHERE tbl_secur.secur_uuid = :securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(UserDto.class);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#fetchAllowedUsers(java.lang.Integer)
     */
    @Override
    public List<String> fetchAllowedUsers() throws Exception {
        final String sqlQuery = "SELECT `secur_uuid` AS secur_uuid FROM tbl_user AS tbl_user"
            + " Join tbl_user_domain AS tbl_userdomain ON tbl_userdomain.user_id=tbl_user.user_id"
            + " WHERE tbl_user.locked_at IS NULL and tbl_userdomain.iref = :iref";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            return query.executeAndFetch(String.class);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#disableUser(java.lang.Long, java.lang.String)
     */
    @Override
    public void disableUser(final Long userId, final String disableDate) throws Exception {
    	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        final String sqlQuery = "update tbl_user set disabled_from = :disabledFrom WHERE user_id = :userId";

        try (final Connection connection = dataSource.beginTransaction()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userId", userId);
            query.addParameter("disabledFrom", disableDate);
            query.executeUpdate();
            connection.commit();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(final String securUuid) throws Exception {

        final UserDto userCredential = getUserCredentialsBySecurUuid(securUuid);
        UserDto user = null;

        final String sqlQuery =
            "SELECT tbl_secur.`secur_uuid` AS securUuid, tbl_secur.`forename`, tbl_secur.`surname`, tbl_secur.`middle`, tbl_secur.`jobtitle` AS jobTitle, "
                + "tbl_secur.`aka` AS displayAs, tbl_secur.`email`, tbl_secur.`office` AS officeId, tbl_offices.`name` AS officeName, "
                + "tbl_offices.`office_uuid` AS officeUuid, tbl_secur.`tel1` AS mobile, tbl_secur.`tel2` AS fax, tbl_secur.`endsleigh_id` AS referencingId, "
                + "tbl_secur.`maxresults` AS maxResults, tbl_secur.`diaryclashcheck` AS diaryClashLevel, tbl_secur.`comm` AS commission, tbl_secur.`mailservers_id` AS mailServerId, "
                + "tbl_mailservers.`name` AS mailServerName, tbl_mailservers.`mailserver_uuid` AS mailServerUuid, tbl_secur.`emailusername` AS emailUser, tbl_secur.`emailpass` AS emailPassword, "
                + "tbl_secur.`docmgtuser` AS docMgtUser, tbl_secur.`docmgtpass` AS docMgtPassword, tbl_secur.`descr` AS description, tbl_secur.`passexp` AS expires, "
                + "tbl_secur.`login_count` AS allowedInstances, tbl_secur.`neg` AS negotiator, tbl_secur.`omitdiary` AS omitDiary, tbl_secur.`disable` AS disableVisibility "
                + "FROM `tbl_secur` AS tbl_secur LEFT JOIN `tbl_offices` AS tbl_offices ON tbl_offices.`offices_id` = tbl_secur.`office` "
                + "LEFT JOIN `tbl_mailservers` AS tbl_mailservers ON tbl_mailservers.`mailservers_id` = tbl_secur.`mailservers_id` "
                + "WHERE tbl_secur.secur_uuid = :securUuid";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            user = query.executeAndFetchFirst(UserDto.class);
        }
        user.setUserName(userCredential.getUserName());
        // user.setPassword(userCredential.getPassword());
        return user;
    }

    /**
     * Gets the user credentials by secur uuid.
     *
     * @param securUuid the secur uuid
     * @return the user credentials by secur uuid
     * @throws Exception the exception
     */
    public UserDto getUserCredentialsBySecurUuid(final String securUuid) throws Exception {

        final String sqlQuery = "SELECT `tbl_user`.`logname` AS username,"
            + " `tbl_user`.`logpass` as password "
            + " FROM tbl_user AS tbl_user "
            + " Join tbl_user_domain AS tbl_userdomain ON tbl_userdomain.user_id=tbl_user.user_id"
            + " WHERE ((tbl_userdomain.secur_uuid = :securId)) LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securId", securUuid);
            return query.executeAndFetchFirst(UserDto.class);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#getIdByUuid(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Long getIdByUuid(final String tableName, final String idFieldName, final String uuidFieldName, final String uuid) throws Exception {

        final Long id;
        final String sqlQuery = "SELECT " + idFieldName + " FROM " + tableName + " WHERE " + uuidFieldName + "=:uuid";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("uuid", uuid);
            id = query.executeAndFetchFirst(Long.class);
        }
        return id;
    }

	@Override
	public Long saveContactDetails(UserDto userDetail) throws Exception {
		
		final String sqlQuery = "INSERT INTO cst_contacts(`address`, `city`, `state`, `country`, `pincode`, `mobile`, `landline`, `email`)"
				+ " VALUES(:address, :city, :state, :country, :pincode, :mobile, :landline, :email)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("address", userDetail.getAddress());
            query.addParameter("city", userDetail.getCity());
            query.addParameter("state", userDetail.getState());
            query.addParameter("country", userDetail.getCountry());
            query.addParameter("pincode", userDetail.getPincode());
            query.addParameter("mobile", userDetail.getMobile());
            query.addParameter("landline", userDetail.getLandline());
            query.addParameter("email", userDetail.getEmail());
            return (Long) query.executeUpdate().getKey();
        }
	}

	

}