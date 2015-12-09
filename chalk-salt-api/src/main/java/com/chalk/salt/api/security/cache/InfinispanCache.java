package com.chalk.salt.api.security.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * The Class InfinispanCache.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
public class InfinispanCache<K, V> implements Cache<K, V> {

    /** The cache proxy. */
    private final org.infinispan.Cache<K, V> cacheProxy;

    /**
     * Instantiates a new infinispan cache.
     *
     * @param cacheProxy the cache proxy
     */
    public InfinispanCache(final org.infinispan.Cache<K, V> cacheProxy) {
        this.cacheProxy = cacheProxy;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#get(java.lang.Object)
     */
    @Override
    public V get(final K key) throws CacheException {
        if (key == null) {
            return null;
        }
        return cacheProxy.get(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public V put(final K key, final V value) throws CacheException {
        return cacheProxy.put(key, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#remove(java.lang.Object)
     */
    @Override
    public V remove(final K key) throws CacheException {
        if (key == null) {
            return null;
        }
        return cacheProxy.remove(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#clear()
     */
    @Override
    public void clear() throws CacheException {
        cacheProxy.clear();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#size()
     */
    @Override
    public int size() {
        return cacheProxy.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#keys()
     */
    @Override
    public Set<K> keys() {
        return cacheProxy.keySet();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.cache.Cache#values()
     */
    @Override
    public Collection<V> values() {
        return cacheProxy.values();
    }

}
