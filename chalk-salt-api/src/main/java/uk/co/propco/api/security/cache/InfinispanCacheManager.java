package com.chalk.salt.api.security.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.infinispan.manager.CacheContainer;

/**
 * The Class InfinispanCacheManager.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 *
 */
public class InfinispanCacheManager implements CacheManager {

    /** The cache container. */
    private final CacheContainer cacheContainer;

    /**
     * Instantiates a new infinispan cache manager.
     *
     * @param cacheContainer the cache container
     */
    public InfinispanCacheManager(final CacheContainer cacheContainer) {
        this.cacheContainer = cacheContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.CacheManager#getCache(java.lang.String)
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <K, V> Cache<K, V> getCache(final String name) throws CacheException {
        return new InfinispanCache(cacheContainer.getCache(name));
    }

}
