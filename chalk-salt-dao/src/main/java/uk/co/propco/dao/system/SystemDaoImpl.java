/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.system;

import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.dto.SystemDetail;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class UserDaoImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class SystemDaoImpl implements SystemDao {

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.dao.system.SystemDao#obtainUserSystemDetails(java.lang.String, java.lang.Long)
     */
    @Override
    public SystemDetail obtainUserSystemDetails(final String systemJndiName, final Long iRef) throws UserException {
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(systemJndiName);
        final String sqlQuery = "SELECT `netloc`.`auth_type` AS authenticationType, `netloc`.`netname` AS officeJndi, `netloc`.`login_attempts` AS numberOfLoginAttempts, `netloc`.`no_of_license` AS numberOfLicense, "
                + " `name` as officeDatabase FROM netloc AS netloc " + "WHERE ((netloc.iref = :iref) AND  (netloc.active = true))";

        SystemDetail systemDetail = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("iref", iRef);
            systemDetail = query.executeAndFetchFirst(SystemDetail.class);
        }
        return systemDetail;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.co.techblue.propco.enterprise.dao.system.SystemDao#isIPAddressAllowed(java.lang.Long, java.lang.String,
     * java.lang.String)
     */
    @Override
    public boolean isIPAddressAllowed(final Long iRef, final String commaSeperatedIpAddress, final String systemJndiName) throws UserException {
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(systemJndiName);
        final String sqlQuery = "SELECT `tbl_security`.`network` " + "FROM tbl_security AS tbl_security "
                + "WHERE ((tbl_security.iref = :iref) AND (tbl_security.network = :ipAddress))";

        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("iref", iRef);
            query.addParameter("ipAddress", commaSeperatedIpAddress);
            final Table table = query.executeAndFetchTable();
            final List<Row> rows = table.rows();
            if (rows != null && !rows.isEmpty()) {
                return true;
            }
            return false;
        }
    }

}
