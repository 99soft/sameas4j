package org.nnsoft.sameas4j.cache;

/**
 * 
 */
public interface Cache {

    <T> void put(CacheKey cacheKey, T cacheValue);

    <T> T get(CacheKey cacheKey, Class<T> type);

}
