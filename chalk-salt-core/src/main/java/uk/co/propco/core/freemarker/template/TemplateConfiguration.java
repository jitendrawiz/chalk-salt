/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.core.freemarker.template;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;




/**
 * The Class TempletConfiguration.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
@ApplicationScoped
public class TemplateConfiguration {

    /** The templet loader from database. */
    @Inject
    private StringTemplateLoaderFromDatabase templetLoaderFromDatabase;

    /** The configuration. */
    private Configuration configuration;

    /**
     *  The configuration.
     *
     * @return the free marker configuraiton
     */

    /**
     * Gets the free marker configuraiton.
     *
     * @return the free marker configuraiton
     */

   
    @PostConstruct
    public void getNotificationTempletConfiguraiton() {
        configuration=new Configuration(new Version(2, 3, 20));
        configuration.setTemplateUpdateDelayMilliseconds(1000);
        configuration. setLocalizedLookup(false);
        configuration.setTemplateLoader(templetLoaderFromDatabase);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.UK);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
    }

    /**
     * Gets the configuration.
     *
     * @return the configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }


}
