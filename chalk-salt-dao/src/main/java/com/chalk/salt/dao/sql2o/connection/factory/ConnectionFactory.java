package com.chalk.salt.dao.sql2o.connection.factory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.sql2o.Sql2o;

import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.exceptions.UserException;

/**
 * The Class Sql2oManagerImpl.
 */
@Singleton
public class ConnectionFactory {

    /** The logger. */
    @Inject
    @AppLogger
    private static Logger logger;

    /** The sql2o connections. */
    private static final Map<String, Sql2o> sql2oConnections = new HashMap<String, Sql2o>();

    public static synchronized Sql2o provideSql2oInstance(final String jndiName) throws UserException {
        if (sql2oConnections.containsKey(jndiName)) {
            return sql2oConnections.get(jndiName);
        }

        final String dataSourceJndi = ChalkSaltConstants.JNDI_PREFIX + jndiName;
        DataSource dataSource = null;
        try {
            final InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(dataSourceJndi);
        } catch (final NamingException namingException) {
            logger.error("Error occurred while obtaining the initial context for domain datasource against jndiName '" + jndiName + "', Error - ",
                    namingException);
            throw new UserException("Error occurred while obtaining the initial context for domain datasource against jndiName '" + jndiName + "', Error - ",
                    namingException);
        }
        final Sql2o sql2oInstance = new Sql2o(dataSource);
        sql2oConnections.put(jndiName, sql2oInstance);
        return sql2oInstance;
    }

    /**
     * Clear connection registry.
     */
    public static void clearConnectionRegistry() {
        sql2oConnections.clear();
    }

}
