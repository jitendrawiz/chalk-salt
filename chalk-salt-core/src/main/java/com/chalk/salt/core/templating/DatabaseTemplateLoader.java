package com.chalk.salt.core.templating;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.common.dto.NotificationTemplate;
import com.chalk.salt.common.dto.NotificationTemplateRequest;
import com.chalk.salt.common.exceptions.NotificationException;
import com.chalk.salt.dao.notification.manager.NotificationManager;

import freemarker.cache.TemplateLoader;

public class DatabaseTemplateLoader implements TemplateLoader {

    /** The notification manager. */
    @Inject
    private transient NotificationManager notificationManager;

    /** The Constant currentTemplate. */
    private static final ThreadLocal<NotificationTemplate> currentTemplate = new ThreadLocal<NotificationTemplate>();

    /** The Constant LOGGER. */
    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseTemplateLoader.class);

    /** The Constant INCLUDE_TEMPLATE_PREFIX. */
    private static final String INCLUDE_TEMPLATE_PREFIX = "INCLUDE_";

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.cache.TemplateLoader#closeTemplateSource(java.lang.Object)
     */
    @Override
    public void closeTemplateSource(final Object notificationTemplate) throws IOException {

    }

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.cache.TemplateLoader#findTemplateSource(java.lang.String)
     */
    @Override
    public Object findTemplateSource(final String name) throws IOException {
        NotificationTemplateRequest templateRequest = DatabaseTemplateConfiguration.getCurrentTemplateRequest();
        if (templateRequest == null) {
            throw new IllegalStateException("No NotificationTemplateRequest instance is associated with current thread against template name '" + name + "'");
        }
        NotificationTemplate notificationTemplate = null;

        // final DomainUserPrincipalDto domainDto=(DomainUserPrincipalDto)TemplateClient.getThreadLocal().get();

        final boolean isInclude = name.startsWith(INCLUDE_TEMPLATE_PREFIX);
        if (isInclude) {
            templateRequest = getIncludeTemplateRequest(name, templateRequest.getTemplateKey());
        }
        if (StringUtils.isNotBlank(templateRequest.getTemplateKey())) {
            try {
                notificationTemplate = notificationManager.getNotificationTemplate(templateRequest.getTemplateKey());
            } catch (final NotificationException ne) {
                LOGGER.error("Error occured while getting the notification template against the template key '{}' ,Error-", templateRequest.getTemplateKey(), ne);
                throw new IOException("Could not obtain the notification template against the template key '" + templateRequest.getTemplateKey() + "', Error-"
                    + ne.getMessage());
            }
        }
        notificationTemplate.setMergeBodyInTemplate(templateRequest.getMergeBodyInTemplate());
        notificationTemplate.setMergeSubject(templateRequest.isMergeSubject());
        if (!isInclude) {
            currentTemplate.set(notificationTemplate);
        }
        return notificationTemplate;
    }

    /**
     * Gets the include template request.
     *
     * @param templateName the template name
     * @param templateKey the template key
     * @return the include template request
     */
    private NotificationTemplateRequest getIncludeTemplateRequest(final String templateName, final String templateKey) {
        final NotificationTemplateRequest templateInclude = new NotificationTemplateRequest();
        templateInclude.setTemplateKey(templateName);
        templateInclude.setMergeBodyInTemplate(true);
        return templateInclude;
    }

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.cache.TemplateLoader#getLastModified(java.lang.Object)
     */
    @Override
    public long getLastModified(final Object arg0) {
        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see freemarker.cache.TemplateLoader#getReader(java.lang.Object, java.lang.String)
     */
    @Override
    public Reader getReader(final Object templateSource, final String encoding) throws IOException {
        final NotificationTemplate notificationTemplate = (NotificationTemplate) templateSource;
        String template = null;
        if (!notificationTemplate.getMergeBodyInTemplate()) {
            template = notificationTemplate.getEditableContent();
        } else if (notificationTemplate.isMergeSubject()) {
            template = notificationTemplate.getSubject();
        } else {
            template = notificationTemplate.getPrimaryContent();
        }
        return new StringReader(template);
    }

    /**
     * Gets the current notification template source.
     * 
     * @return the current notification template source
     */
    public static NotificationTemplate getCurrentNotificationTemplateSource() {
        return currentTemplate.get();
    }

}
