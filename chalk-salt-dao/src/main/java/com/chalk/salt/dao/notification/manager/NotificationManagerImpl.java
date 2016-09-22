package com.chalk.salt.dao.notification.manager;

import javax.inject.Inject;

import com.chalk.salt.common.dto.NotificationTemplate;
import com.chalk.salt.common.exceptions.NotificationException;
import com.chalk.salt.dao.notification.NotificationDao;

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
