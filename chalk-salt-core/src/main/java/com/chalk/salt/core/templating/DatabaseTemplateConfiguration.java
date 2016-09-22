package com.chalk.salt.core.templating;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import com.chalk.salt.common.dto.NotificationTemplate;
import com.chalk.salt.common.dto.NotificationTemplateRequest;
import com.chalk.salt.common.exceptions.TemplateProcessingException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DatabaseTemplateConfiguration extends Configuration {

    /** The Constant DEFAULT_ENCODING. */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /** The Constant currentTemplateRequest. */
    private static final ThreadLocal<NotificationTemplateRequest> currentTemplateRequest = new ThreadLocal<NotificationTemplateRequest>();

    /**
     * Instantiates a new database template configuration.
     */
    @SuppressWarnings("deprecation")
    public DatabaseTemplateConfiguration() {

    }

    /**
     * Gets the current template request.
     * 
     * @return the current template request
     */
    public static NotificationTemplateRequest getCurrentTemplateRequest() {
        return currentTemplateRequest.get();
    }

    /**
     * Gets the template.
     * 
     * @param templateRequest the template request
     * @return the template
     * @throws TemplateProcessingException the template processing exception
     */
    public String processTemplate(final NotificationTemplateRequest templateRequest) throws TemplateProcessingException {
        return processTemplate(templateRequest, true);
    }

    /**
     * Gets the template.
     * 
     * @param templateRequest the template request
     * @param parse the parse
     * @return the template
     * @throws TemplateProcessingException the template processing exception
     */
    public String processTemplate(final NotificationTemplateRequest templateRequest, final boolean parse) throws TemplateProcessingException {
        return processTemplate(templateRequest, Locale.getDefault(), DEFAULT_ENCODING, parse);
    }

    /**
     * Gets the template.
     * 
     * @param templateRequest the template request
     * @param locale the locale
     * @return the template
     * @throws TemplateProcessingException the template processing exception
     */
    public String processTemplate(final NotificationTemplateRequest templateRequest, final Locale locale) throws TemplateProcessingException {
        return processTemplate(templateRequest, locale, DEFAULT_ENCODING, true);
    }

    /**
     * Process template.
     * 
     * @param templateRequest the template request
     * @param locale the locale
     * @param encoding the encoding
     * @param parse the parse
     * @return the string
     * @throws TemplateProcessingException the template processing exception
     */
    public String processTemplate(final NotificationTemplateRequest templateRequest, final Locale locale, final String encoding, final boolean parse)
        throws TemplateProcessingException {
        currentTemplateRequest.set(templateRequest);
        try {
            Template template = null;
            try {
                template = super.getTemplate(templateRequest.getTemplateKey(), locale, encoding, parse);
            } catch (final IOException ioe) {
                throw new TemplateProcessingException("An error occurred while getting template against key '" + templateRequest.getTemplateKey() + "'", ioe);
            }

            final StringWriter processedSource = new StringWriter();
            try {
                template.process(templateRequest.getDataMap(), processedSource);
            } catch (final TemplateException te) {
                throw new TemplateProcessingException("An error occurred while processing template against key '" + templateRequest.getTemplateKey() + "'", te);
            } catch (final IOException ioe) {
                throw new TemplateProcessingException("An error occurred while processing template against key '" + templateRequest.getTemplateKey() + "'", ioe);
            }
            return processedSource.toString();
        } finally {
            currentTemplateRequest.remove();
        }
    }

    /**
     * Gets the current notification template source.
     * 
     * @return the current notification template source
     */
    public NotificationTemplate getCurrentNotificationTemplateSource() {
        return DatabaseTemplateLoader.getCurrentNotificationTemplateSource();
    }

}
