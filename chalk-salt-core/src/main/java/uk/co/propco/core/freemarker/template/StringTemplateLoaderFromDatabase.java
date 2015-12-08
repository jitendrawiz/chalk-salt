/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.core.freemarker.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.inject.Inject;

import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.dao.notification.manager.NotificationManager;
import freemarker.cache.TemplateLoader;


/**
 * The Class StringTempletLoaderFromDatabase.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class StringTemplateLoaderFromDatabase implements TemplateLoader {
    
    /** The notification template manager. */
    @Inject
    private NotificationManager notificationTemplateManager;
    
    /* (non-Javadoc)
     * @see freemarker.cache.TemplateLoader#findTemplateSource(java.lang.String)
     */
    @Override
    public Object findTemplateSource(final String name) throws IOException {
        
        String templateSource = null;
        DomainUserPrincipalDto domainDto=(DomainUserPrincipalDto)TemplateClient.getThreadLocal().get();
        try {
            
            templateSource = notificationTemplateManager.getNotificationTemplate(name, domainDto);
        } catch (UserException e) {
            
        }
        return templateSource;       
    }

    /* (non-Javadoc)
     * @see freemarker.cache.TemplateLoader#getLastModified(java.lang.Object)
     */
    @Override
    public long getLastModified(final Object templateSource) {
        return -1;
    }

    /* (non-Javadoc)
     * @see freemarker.cache.TemplateLoader#getReader(java.lang.Object, java.lang.String)
     */
    @Override
    public Reader getReader(final Object templateSource, final String encoding) throws IOException {        
        final String template = (String) templateSource;
        return new StringReader(template);
    }

    /* (non-Javadoc)
     * @see freemarker.cache.TemplateLoader#closeTemplateSource(java.lang.Object)
     */
    @Override
    public void closeTemplateSource(final Object templateSource) throws IOException {

    }
   
}
