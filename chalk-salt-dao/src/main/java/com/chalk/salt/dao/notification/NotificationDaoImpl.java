/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.notification;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.chalk.salt.common.dto.ChalkSaltConstants;
import com.chalk.salt.common.dto.NotificationTemplate;
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
    public NotificationTemplate getNotificationTemplate(final String key) throws NotificationException{   
        
    	final String sqlQuery =
                "SELECT     `id` AS notificationTemplateId, `primary_content` AS primaryContent, `editable_content` AS editableContent,"
                    + "`subject` AS SUBJECT, `notification_recipient_type` AS recipientType, `notification_template_key` AS notificationTemplateKey,"
                    + "`notification_type` AS notificationType, `merge_body_in_template` AS mergeBodyInTemplate, `internal` AS internal,"
                    + "`recipient_id` AS recipientId, `merge_subject` AS mergeSubject FROM `cst_template` WHERE `notification_template_key` = :templateKey";

            
            try {
            	final Sql2o dataSource = ConnectionFactory.provideSql2oInstance(ChalkSaltConstants.DOMAIN_DATASOURCE_JNDI_NAME);

                try (final Connection connection = dataSource.open()) {
                    final Query query = connection.createQuery(sqlQuery);
                    query.addParameter("templateKey", key);
                    return query.executeAndFetchFirst(NotificationTemplate.class);
                }
            } catch (final UserException e) {
                throw new NotificationException(e);
            }
    }

}
