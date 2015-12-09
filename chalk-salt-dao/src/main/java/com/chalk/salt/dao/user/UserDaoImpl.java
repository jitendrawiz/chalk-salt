/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.PropcoConstants;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.dto.UserDetailDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.Utility;
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
    public UserDetailDto fetchUserDetails(final String securUuid, final String officeJndi) throws Exception {
        final String sqlQuery =
            "SELECT `tbl_secur`.`secur_uuid` AS securUuid, `tbl_secur`.`forename` AS forename, `tbl_secur`.`aka` AS displayAs, "
                + " `tbl_secur`.`middle` AS middle, `tbl_secur`.`surname` AS surname, `tbl_secur`.`email` as email FROM tbl_secur AS tbl_secur "
                + "WHERE ((tbl_secur.secur_uuid = :securUuid))";

        final Sql2o datasource = ConnectionFactory.provideSql2oInstance(officeJndi);
        UserDetailDto userDetail = null;
        try (final Connection connection = datasource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            userDetail = query.executeAndFetchFirst(UserDetailDto.class);
        }
        return userDetail;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.dao.office.OfficeDao#saveUserLoginStatus(java.lang.Long, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean saveUserLoginStatus(final Long userId, final String userName, final String commaSeperatedIpAddress, final String officeJndi,
        final String status) throws Exception {
        final String sqlQuery = "INSERT  INTO tbl_logins (`login_id`,`username`, `ipaddress`, `login`, `status`) "
            + "values (:loginId, :logname, :ipAddress, :loginDateTime , :loginStatus)";

        final Sql2o datasource = ConnectionFactory.provideSql2oInstance(officeJndi);
        try (final Connection connection = datasource.beginTransaction()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("loginId", userId);
            query.addParameter("logname", userName);
            query.addParameter("ipAddress", commaSeperatedIpAddress);
            query.addParameter("loginDateTime", Utility.convertDateToString("yyyy-MM-dd HH:mm:ss", new Date()));
            query.addParameter("loginStatus", status);
            query.executeUpdate();
            connection.commit();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#isUserExits(java.lang.String)
     */
    @Override
    public boolean isUserExist(final String username) throws Exception {
        final String sqlQuery = "SELECT `tbl_user`.`logname` AS logname  "
            + " FROM tbl_user AS tbl_user "
            + "WHERE ((tbl_user.logname = :username))";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
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
    public Long getUserIdBySecurUuid(final String securUuid) throws Exception {
        Long userId = null;
        final String sqlQuery = "SELECT `tbl_user_domain`.`user_id` AS userId "
            + " FROM tbl_user_domain AS tbl_user_domain "
            + "WHERE ((tbl_user_domain.secur_uuid = :securId)) LIMIT 1";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
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
     * @see com.chalk.salt.dao.user.UserDao#getMasterIdBySystemJndi(java.lang.String)
     */
    public Long getMasterIdBySystemJndi(final String systemJndi) throws Exception {
        final String sqlQuery = "SELECT `tbl_clients`.`master_id` AS masterId  "
            + " FROM tbl_clients AS tbl_clients "
            + "WHERE ((tbl_clients.jndisys = :jndisys))";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("jndisys", systemJndi);
            return query.executeAndFetchFirst(Long.class);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#saveUser(java.lang.String, java.lang.String)
     */
    @Override
    public Long saveLoginDetails(final String username, final String hashedPassword) throws Exception {
        final String sqlQuery = "INSERT  INTO tbl_user (`logname`,`logpass`) "
            + "values (:logname, :logpass) ON DUPLICATE KEY "
            + "UPDATE `logname` = :logname, `logpass` = :logpass";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
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
     * @see com.chalk.salt.dao.user.UserDao#getIrefByIrefUuid(java.lang.String, java.util.List)
     */
    public List<Row> fetchSystemDataByIrefUuid(final String systemJndi, final String irefUuid) throws Exception {
        final String sqlQuery = "SELECT `netloc`.`iref` AS iref , `netloc`.`netname` "
            + " FROM netloc AS netloc "
            + "WHERE ((netloc.iref_uuid= :irefUuid)) ";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(systemJndi);

        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("irefUuid", irefUuid);
            final Table table = query.executeAndFetchTable();
            return table.rows();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#saveDomainDetail(java.lang.Long, java.lang.String, java.lang.Long, java.lang.Integer,
     * java.lang.Boolean)
     */
    @Override
    public boolean saveDomainDetail(final Long userId, final String securUuid, final Long masterId, final Integer iref, final boolean defaultDomain) throws Exception {
        final String sqlQuery = "INSERT  INTO tbl_user_domain ( `user_id`, `secur_uuid`, `master_id`, `iref`, `is_default` ) "
            + " values (:userId, :securUuid, :masterId, :iref, :defaultDomain)";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userId", userId);
            query.addParameter("securUuid", securUuid);
            query.addParameter("masterId", masterId);
            query.addParameter("iref", iref);
            query.addParameter("defaultDomain", defaultDomain);
            query.executeUpdate();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#saveUserDetails(java.lang.String, com.chalk.salt.common.dto.UserDetail, java.util.List)
     */
    @Override
    public boolean saveUserDetails(final UserDto userDetail, final String officeJndi) throws Exception {
        
        final String sqlQuery = "INSERT  INTO tbl_secur (`secur_uuid`, `forename`, `surname`, `middle`, `jobtitle` , `aka` , `email`, "
            + "`office` , `tel1` , `tel2` , `endsleigh_id`, `maxresults` , `diaryclashcheck` , `comm`, `mailservers_id`, `emailusername`, "
            + "`emailpass`, `docmgtuser`, `docmgtpass`, `descr`, `passexp`, `login_count`, `neg`, `omitdiary`, `disable`) "
            + " values (:secur_uuid, :forename, :surname, :middle, :jobtitle , :aka , :email, :office, :tel1, :tel2, :endsleigh_id, "
            + ":maxresults, :diaryclashcheck, :comm, :mailservers_id, :emailusername, :emailpass, :docmgtuser, :docmgtpass, :descr, "
            + ":passexp, :login_count, :neg, :omitdiary, :disable)";
                
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(officeJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
                      
            query.addParameter("secur_uuid", userDetail.getSecurUuid());
            query.addParameter("forename", userDetail.getForename());
            query.addParameter("surname", userDetail.getForename());
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
     * @see com.chalk.salt.dao.user.UserDao#getDomainList(java.lang.String, java.lang.String)
     */

    @Override
    public List<Integer> fetchIrefBySystemJndi(final String securUuid, final String systemJndi) throws Exception {
        String sqlQuery = "SELECT `tbl_user_domain`.`iref` AS iref "
            + " FROM tbl_user_domain AS tbl_user_domain "
            + "WHERE ((tbl_user_domain.secur_uuid = :securId))";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securId", securUuid);
            return query.executeAndFetch(Integer.class);

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#fetchDomainList(java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public List<DomainDetailDto> fetchDomainDetailByIref(final String systemJndi, final List<Integer> iref) throws Exception {
        final String sqlQuery = "SELECT `netloc`.`iref_uuid` AS irefUuid, `netloc`.`name` AS domainName "
            + " FROM netloc AS netloc "
            + "WHERE ((netloc.iref IN (" + StringUtils.join(iref.toArray(), ',') + ") )) ";
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(systemJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            return query.executeAndFetch(DomainDetailDto.class);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#fetchUserIconList(java.lang.String, java.lang.String)
     */
    @Override
    public List<UserIconDto> fetchIcons(final String securUuid, final String officeJndi) throws Exception {
        final Long userId = getUserIdBySecurUuid(securUuid);
        final String sqlQuery = "SELECT `tbl_iconmaster`.`icon` AS icon, `tbl_iconmaster`.`link` AS link, `tbl_iconmaster`.`name` AS name "
            + " FROM tbl_usericon AS tbl_usericon "
            + " Join tbl_iconmaster AS tbl_iconmaster ON tbl_iconmaster.icon=tbl_usericon.icon "
            + "WHERE ((tbl_usericon.user_id = :userId))";

        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(officeJndi);
        List<UserIconDto> userIcons = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userId", userId);
            userIcons = query.executeAndFetch(UserIconDto.class);
        }
        return userIcons;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chalk.salt.dao.user.UserDao#saveUserHomeIcons(com.chalk.salt.common.dto.UserIconDto)
     */
    @Override
    public boolean saveIcons(final Long userId, final String officeJndi, final UserIconDto userIcon)
        throws Exception {
        final Sql2o datasource = ConnectionFactory.provideSql2oInstance(officeJndi);
        String iconName = userIcon.getIcon();
        final String sqlQuery = "INSERT  INTO tbl_usericon (`icon`,`user_id`) "
            + "values (:iconName, :userId) ";
        try (final Connection connection = datasource.beginTransaction()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("iconName", iconName);
            query.addParameter("userId", userId);
            query.executeUpdate();
            connection.commit();
        }
        return true;
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#lockUser(com.chalk.salt.common.dto.SaveLoginRequestDto, java.util.Date, java.util.Date)
     */
    @Override
    public int getFailedLoginAttempts(String officeJndi, SaveLoginRequestDto saveLoginRequest, String currentDateTime, String nextDateTime) throws Exception {
        
        final String sqlQuery = "SELECT count(`login_id`) AS ids FROM tbl_logins AS tbl_logins "
            + "WHERE ((tbl_logins.login_id = :loginId AND tbl_logins.status = :status AND tbl_logins.logout IS NULL AND (tbl_logins.login > :nextDateTime AND tbl_logins.login < :currentDateTime)))";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(officeJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("loginId", saveLoginRequest.getUserId());
            query.addParameter("status", AuthStatus.REJECTED);            
            query.addParameter("currentDateTime", currentDateTime);
            query.addParameter("nextDateTime", nextDateTime);
            return query.executeAndFetchFirst(Integer.class);            
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#lockUser(com.chalk.salt.common.dto.SaveLoginRequestDto, java.util.Date)
     */
    @Override
    public void lockUser(String officeJndi, SaveLoginRequestDto saveLoginRequest, String currentDate) throws Exception {        
        
        final Sql2o datasource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);        
        final String sqlQuery = "update tbl_user set locked_at = :lockedAt WHERE user_id = :userId AND logname = :userName";
            
        try (final Connection connection = datasource.beginTransaction()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("lockedAt", currentDate);
            query.addParameter("userId", saveLoginRequest.getUserId());
            query.addParameter("userName", saveLoginRequest.getUsername());
            query.executeUpdate();            
            connection.commit();
        }
        
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#fetchUsers(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto fetchUsers(String securUuid, String officeJndi) throws Exception {
        final String sqlQuery = "SELECT `secur_uuid` as securUuid, `forename`, `surname`, `middle`, `jobtitle` as jobTitle FROM `tbl_secur` AS tbl_secur "
            + "WHERE tbl_secur.secur_uuid = :securUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(officeJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            return query.executeAndFetchFirst(UserDto.class);
        }   
    }


    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#fetchAllowedUsers(java.lang.Integer)
     */
    @Override
    public List<String> fetchAllowedUsers(Integer iref) throws Exception {        
        final String sqlQuery = "SELECT `secur_uuid` AS secur_uuid FROM tbl_user AS tbl_user"
            + " Join tbl_user_domain AS tbl_userdomain ON tbl_userdomain.user_id=tbl_user.user_id"
            + " WHERE tbl_user.locked_at IS NULL and tbl_userdomain.iref = :iref";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);   
            query.addParameter("iref", iref);
            return query.executeAndFetch(String.class);
        }
    }
    
    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#fetchIrefByOfficeJndi(java.lang.String, java.lang.String)
     */
    @Override
    public Integer fetchIrefByOfficeJndi(final String officeJndi, final String systemJndi) throws Exception {
        String sqlQuery = "SELECT `netloc`.`iref` AS iref FROM `netloc` AS netloc"
            + " WHERE netname = :officeJndi";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(systemJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("officeJndi", officeJndi);
            return query.executeAndFetchFirst(Integer.class);

        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#disableUser(java.lang.Long, java.lang.String)
     */
    @Override
    public void disableUser(Long userId, String disableDate) throws Exception {
        final Sql2o datasource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);        
        final String sqlQuery = "update tbl_user set disabled_from = :disabledFrom WHERE user_id = :userId";
            
        try (final Connection connection = datasource.beginTransaction()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userId", userId);
            query.addParameter("disabledFrom", disableDate);
            query.executeUpdate();            
            connection.commit();
        }
    }

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.user.UserDao#getUserInfo(java.lang.String, java.lang.String)
     */
    @Override
    public UserDto getUserInfo(String securUuid, String officeJndi) throws Exception {
             
        final UserDetailDto userCredential = getUserCredentialsBySecurUuid(securUuid);
        UserDto user = null;
        
        final String sqlQuery = "SELECT `secur_uuid` as securUuid, `forename`, `surname`, `middle`, `jobtitle` as jobTitle, "
            + " aka as displayAs, email, office as officeId, tel1 as mobile, tel2 as fax, endsleigh_id as referencingId, "
            + "maxresults as maxResults, diaryclashcheck as diaryClashLevel, comm as commission, mailservers_id as mailServerId, "
            + "emailusername as emailUser, emailpass as emailPassword, docmgtuser as docMgtUser, docmgtpass as docMgtPassword, "
            + "descr as description, passexp as expires, login_count as allowedInstances, neg as negotiator, "
            + "omitdiary as omitDiary, disable as disableVisibility FROM `tbl_secur` AS tbl_secur "
            + "WHERE tbl_secur.secur_uuid = :securUuid";
        Sql2o dataSource = ConnectionFactory.provideSql2oInstance(officeJndi);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securUuid", securUuid);
            user =  query.executeAndFetchFirst(UserDto.class);
        }
        user.setUsername(userCredential.getUsername());
        user.setPassword(userCredential.getPassword());
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
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("securId", securUuid);
            return query.executeAndFetchFirst(UserDetailDto.class);
        }
    }
}