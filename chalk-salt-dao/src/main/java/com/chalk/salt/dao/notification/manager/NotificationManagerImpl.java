/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.notification.manager;

import javax.inject.Inject;

import com.chalk.salt.common.dto.NotificationTemplate;
import com.chalk.salt.common.exceptions.NotificationException;
import com.chalk.salt.dao.notification.NotificationDao;

/**
 * The Class NotificationTemplateManagerImpl.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class NotificationManagerImpl implements NotificationManager {

    /** The notification template dao. */
    @Inject    
    private NotificationDao notificationTemplateDao;
    
    /* (non-Javadoc)
     * @see com.chalk.salt.dao.notification.manager.NotificationTemplateManager#getNotificationTemplate(java.lang.String)
     */
    @Override
    public NotificationTemplate getNotificationTemplate(final String key)throws NotificationException {
        return notificationTemplateDao.getNotificationTemplate(key);        
    }

}
