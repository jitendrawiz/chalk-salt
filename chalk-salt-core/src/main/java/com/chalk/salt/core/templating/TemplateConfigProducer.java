/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.core.templating;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The Class TemplateConfigurationProducer.
 *
 * @author <a href="mailto:jitendra.pareek@techblue.co.uk">Jitendra Pareek</a>
 */
public class TemplateConfigProducer {

    /** The database template loader. */
    @Inject
    private DatabaseTemplateLoader databaseTemplateLoader;

    /**
     * Produce mapper.
     *
     * @return the mapper
     */
    @Produces
    @TemplateConfig
    @Singleton
    public DatabaseTemplateConfiguration produceMapper() {

        final DatabaseTemplateConfiguration databaseTemplateConfiguration = new DatabaseTemplateConfiguration();

        databaseTemplateConfiguration.setTemplateUpdateDelayMilliseconds(0);
        databaseTemplateConfiguration.setLocalizedLookup(false);
        databaseTemplateConfiguration.setTemplateLoader(databaseTemplateLoader);
        databaseTemplateConfiguration.setCacheStorage(new freemarker.cache.NullCacheStorage());

        return databaseTemplateConfiguration;
    }
}
