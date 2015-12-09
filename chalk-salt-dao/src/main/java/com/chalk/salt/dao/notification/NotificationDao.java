/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.dao.notification;

import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.exceptions.NotificationException;

/**
 * The Class NotificationTempletDao.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public interface NotificationDao {
    
    /**
     * Gets the notification template.
     *
     * @param key the key
     * @param domainDto the domain dto
     * @return the notification template
     * @throws NotificationException the notification exception
     */
    public String getNotificationTemplate(final String key, final DomainUserPrincipalDto domainDto)throws NotificationException;

}
