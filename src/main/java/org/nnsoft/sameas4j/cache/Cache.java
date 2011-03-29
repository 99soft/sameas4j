package org.nnsoft.sameas4j.cache;

/**
 * 
 * @since 1.2
 * @version $Id$
 */
public interface Cache {

    <T> void put(CacheKey cacheKey, T cacheValue);

    <T> T get(CacheKey cacheKey, Class<T> type);

}
