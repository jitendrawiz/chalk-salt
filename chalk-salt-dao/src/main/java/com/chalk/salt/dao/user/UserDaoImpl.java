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
import com.chalk.salt.common.dto.UserDetailDto;
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
    public UserDetailDto fetchUserDetails(final String securUuid) throws Exception {
        final String sqlQuery =
            "SELECT `tbl_secur`.`secur_uuid` AS securUuid, `tbl_secur`.`forename` AS forename, `tbl_secur`.`aka` AS displayAs, "
                + " `tbl_secur`.`middle` AS middle, `tbl_secur`.`surname` AS surname, `tbl_secur`.`email` as email, locale FROM tbl_secur AS tbl_secur "
                + "WHERE ((tbl_secur.secur_uuid = :securUuid))";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        UserDetailDto userDetail = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            userDetail = query.executeAndFetchFirst(UserDetailDto.class);
        }
        return userDetail;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String username) throws Exception {
        final String sqlQuery = "SELECT `tbl_user`.`logname` AS logname  "
            + " FROM tbl_user AS tbl_user "
            + "WHERE ((tbl_user.logname = :username))";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("username", username);
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
        final String sqlQuery = "INSERT  INTO tbl_user (`logname`,`logpass`) "
            + "values (:logname, :logpass) ON DUPLICATE KEY "
            + "UPDATE `logname` = :logname, `logpass` = :logpass";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery, true);
            query.addParameter("logname", username);
            query.addParameter("logpass", hashedPassword);
            return (Long) query.executeUpdate().getKey();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.propco.dao.user.UserDao#saveUserDetails(java.lang.String, uk.co.propco.common.dto.UserDetail, java.util.List)
     */
    @Override
    public boolean saveUserDetails(final UserDto userDetail, final String officeJndi) throws Exception {

        final String sqlQuery = "INSERT  INTO tbl_secur (`secur_uuid`, `forename`, `surname`, `middle`, `jobtitle` , `aka` , `email`, "
            + "`office` , `tel1` , `tel2` , `endsleigh_id`, `maxresults` , `diaryclashcheck` , `comm`, `mailservers_id`, `emailusername`, "
            + "`emailpass`, `docmgtuser`, `docmgtpass`, `descr`, `passexp`, `login_count`, `neg`, `omitdiary`, `disable`) "
            + " values (:secur_uuid, :forename, :surname, :middle, :jobtitle , :aka , :email, :office, :tel1, :tel2, :endsleigh_id, "
            + ":maxresults, :diaryclashcheck, :comm, :mailservers_id, :emailusername, :emailpass, :docmgtuser, :docmgtpass, :descr, "
            + ":passexp, :login_count, :neg, :omitdiary, :disable)";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);

            query.addParameter("secur_uuid", userDetail.getSecurUuid());
            query.addParameter("forename", userDetail.getForename());
            query.addParameter("surname", userDetail.getSurname());
            query.addParameter("middle", userDetail.getMiddle());
            query.addParameter("jobtitle", userDetail.getJobTitle());
            query.addParameter("aka", userDetail.getDisplayAs());
            query.addParameter("email", userDetail.getEmail());
            query.addParameter("office", userDetail.getOfficeId());
            query.addParameter("tel1", userDetail.getMobile());
            query.addParameter("tel2", userDetail.getFax());
            query.addParameter("endsleigh_id", userDetail.getReferencingId());
            query.addParameter("maxresults", userDetail.getMaxResults());
            query.addParameter("diaryclashcheck", userDetail.getDiaryClashLevel());
            query.addParameter("comm", userDetail.getCommission());
            query.addParameter("mailservers_id", userDetail.getMailServerId());
            query.addParameter("emailusername", userDetail.getUsername());
            query.addParameter("emailpass", userDetail.getEmailPassword());
            query.addParameter("docmgtuser", userDetail.getDocMgtUser());
            query.addParameter("docmgtpass", userDetail.getDocMgtPassword());
            query.addParameter("descr", userDetail.getDescription());
            query.addParameter("passexp", userDetail.getExpires());
            query.addParameter("login_count", userDetail.getAllowedInstances());
            query.addParameter("neg", userDetail.getNegotiator());
            query.addParameter("omitdiary", userDetail.getOmitDiary());
            query.addParameter("disable", userDetail.getDisableVisibility());

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

        final UserDetailDto userCredential = getUserCredentialsBySecurUuid(securUuid);
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
        user.setUsername(userCredential.getUsername());
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
    public UserDetailDto getUserCredentialsBySecurUuid(final String securUuid) throws Exception {

        final String sqlQuery = "SELECT `tbl_user`.`logname` AS username,"
            + " `tbl_user`.`logpass` as password "
            + " FROM tbl_user AS tbl_user "
            + " Join tbl_user_domain AS tbl_userdomain ON tbl_userdomain.user_id=tbl_user.user_id"
            + " WHERE ((tbl_userdomain.secur_uuid = :securId)) LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securId", securUuid);
            return query.executeAndFetchFirst(UserDetailDto.class);
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

}