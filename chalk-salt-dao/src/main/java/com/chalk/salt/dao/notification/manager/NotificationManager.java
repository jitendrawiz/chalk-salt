package com.chalk.salt.dao.notification.manager;

import com.chalk.salt.common.dto.NotificationTemplate;
import com.chalk.salt.common.exceptions.NotificationException;

public interface NotificationManager {
    
    /**
     * Gets the notification template.
     *
     * @param key the key
     * @param domainDto the domain dto
     * @return the notification template
     * @throws NotificationException the notification exception
     */
    public NotificationTemplate getNotificationTemplate(final String key)throws NotificationException;
}
