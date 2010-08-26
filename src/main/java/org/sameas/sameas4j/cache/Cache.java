package org.sameas.sameas4j.cache;

/**
 * 
 * @version $Id$
 */
public interface Cache {

    <T> void put(CacheKey cacheKey, T cacheValue);

    <T> T getEquivalence(CacheKey cacheKey, Class<T> cacheType);

}
