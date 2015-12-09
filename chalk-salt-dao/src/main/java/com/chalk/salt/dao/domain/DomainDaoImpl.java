/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.domain;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.PropcoConstants;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.dto.DomainInfo;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class DomainDaoImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class DomainDaoImpl implements DomainDao {

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.dao.domain.DomainDao#obtainUserDomainDetails(java.lang.String, boolean)
     */
    @Override
    public DomainInfo obtainUserDomainDetails(final String username) throws UserException {
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(PropcoConstants.DOMAIN_DATASOURCE_JNDI_NAME);

        final String sqlQuery = "SELECT `tbl_user`.`user_id` AS 'userId', `tbl_user_domain`.`iref`, `tbl_user_domain`.`master_id` AS masterId, `tbl_user_domain`.`secur_uuid` AS securUuid,"
                + " `tbl_user_domain`.`user_type` AS userType, `tbl_user_domain`.`is_default` AS isDefault, `tbl_clients`.`active` AS 'active', `tbl_clients`.`jndisys` AS 'systemJndi',"
                + " `tbl_user`.`logpass` AS 'password', `tbl_user`.`salt` AS 'salt'  "
                + "FROM tbl_user_domain AS tbl_user_domain "
                + "JOIN tbl_clients tbl_clients ON tbl_clients.master_id = tbl_user_domain.master_id "
                + "JOIN tbl_user tbl_user ON tbl_user.user_id = tbl_user_domain.user_id  "
                + "WHERE ((tbl_clients.active) "
                + "AND (tbl_user_domain.is_default) " + "AND (tbl_user.locked_at is null) " + "AND (tbl_user.logname = :username))";

        DomainInfo domainInfo = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("username", username);
            domainInfo = query.executeAndFetchFirst(DomainInfo.class);
        }
        return domainInfo;
    }

}
