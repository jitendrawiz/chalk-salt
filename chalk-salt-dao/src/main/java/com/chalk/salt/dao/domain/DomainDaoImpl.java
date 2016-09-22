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
 */
public class DomainDaoImpl implements DomainDao {

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.domain.DomainDao#obtainUserLoginDetails(java.lang.String)
     */
    @Override
    public UserDto obtainUserLoginDetails(final String userName) throws UserException {
        final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);

        final String sqlQuery = " SELECT login.user_id AS userId,"
        		+ " login.username AS userName,"
        		+ " login.password, users.first_name AS firstName,"
        		+ " users.middle_name AS middleName, users.last_name AS lastName,"
        		+ " users.secur_uuid AS securUuid, login.active AS active,"
        		+ " contacts.`email` AS email FROM cst_logins AS login"
        		+ " LEFT JOIN cst_users AS users ON users.user_id = login.user_id"
        		+ " LEFT JOIN cst_contacts AS contacts ON users.contact_id = contacts.id"
        		+ " WHERE username=:userName";

        UserDto userDto = null;
        try (final Connection connection = dataSource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("userName", userName);
            userDto = query.executeAndFetchFirst(UserDto.class);
        }
        return userDto;
    }

}
