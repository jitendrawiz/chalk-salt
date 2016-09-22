package com.chalk.salt.core.templating;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The Class TemplateConfigProducer.
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
