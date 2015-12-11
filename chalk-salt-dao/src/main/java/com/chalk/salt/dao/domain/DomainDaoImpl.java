/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.domain;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
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
    public UserDto obtainUserLoginDetails(final String userName) throws UserException {
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);

        final String sqlQuery = "SELECT login.user_id AS userId, login.username AS UserName, login.password, "
        		+ "user.first_name as firstName, user.middle_name as middleName, user.last_name as lastName, secur_uuid as securUuid, "
        		+ "login.active as active, contacts.`email` as email FROM cst_logins as login "
        		+ "JOIN cst_users as user on user.user_id = login.user_id "
        		+ "JOIN cst_contacts as contacts on user.contact_id = contacts.id WHERE username= :userName";

        UserDto userDto = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userName", userName);
            userDto = query.executeAndFetchFirst(UserDto.class);
        }
        return userDto;
    }

}
