
package com.chalk.salt.dao.sql2o.connection.factory;

import org.sql2o.Sql2o;

import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface Sql2oManager.
 */
public interface Sql2oManager {

    /**
     * Provide sql2o instance.
     *
     * @param jndiName the jndi name
     * @return the sql2o
     * @throws UserException the generic exception
     */
    Sql2o provideSql2oInstance(final String jndiName) throws UserException;

}
