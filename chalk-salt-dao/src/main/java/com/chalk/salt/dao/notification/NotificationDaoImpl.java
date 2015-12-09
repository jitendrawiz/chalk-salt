/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.notification;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.exceptions.NotificationException;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.sql2o.connection.factory.ConnectionFactory;

/**
 * The Class NotificationTemplateDaoImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class NotificationDaoImpl implements NotificationDao {

    /* (non-Javadoc)
     * @see com.chalk.salt.dao.notification.NotificationTemplateDao#getNotificationTemplate(java.lang.String)
     */
    @Override
    public String getNotificationTemplate(final String key, final DomainUserPrincipalDto domainDto) throws NotificationException{   
        
        String template = null;
        final String officeJndi = domainDto.getOfficeJndi();
        final String sqlQuery =
            "SELECT `value` AS primaryContent FROM `tbl_template` AS tbl_template WHERE tbl_template.`deleted` = 0 AND tbl_template.`key` = :templateKey";
        Sql2o datasource;
        try {
            datasource = ConnectionFactory.provideSql2oInstance(officeJndi);
        
       
        try (final Connection connection = datasource.open()) {
            final Query query = connection.createQuery(sqlQuery);
            query.addParameter("templateKey", key);
            template = query.executeScalar(String.class);
        }
        } catch (UserException e) {
            new NotificationException(e);
        }
        return template;
    }

}
