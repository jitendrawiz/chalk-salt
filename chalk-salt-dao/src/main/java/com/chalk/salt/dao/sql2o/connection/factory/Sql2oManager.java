/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.sql2o.connection.factory;

import org.sql2o.Sql2o;

import com.chalk.salt.common.exceptions.UserException;

/**
 * The Interface Sql2oManager.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
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
