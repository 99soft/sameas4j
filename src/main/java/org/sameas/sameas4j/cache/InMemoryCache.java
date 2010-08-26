package org.sameas.sameas4j.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @version $Id$
 */
public final class InMemoryCache implements Cache {

    /**
     * The fixed cache size.
     */
    private static final int CACHE_SIZE = 255;

    /**
     * The fixed cache load facor.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * The fixed cache capacity.
     */
    private static final int CACHE_CAPACITY = (int) Math.ceil(CACHE_SIZE / LOAD_FACTOR) + 1;

    /**
     * The map that implements the LRU cache.
     */
    private final Map<CacheKey, Object> data = new LinkedHashMap<CacheKey, Object>(CACHE_CAPACITY, LOAD_FACTOR) {
        /**
         * This class serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<CacheKey, Object> eldest) {
            return size() > CACHE_SIZE;
        }
    };

    public <T> T get(CacheKey cacheKey, Class<T> type) {
        if (cacheKey == null) {
            throw new IllegalArgumentException("Parameter 'cacheKey' must be not null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Parameter 'type' must be not null");
        }

        Object cached = this.data.get(cacheKey);
        if (cached != null) {
            if (!type.isInstance(cached)) {
                throw new IllegalArgumentException("Cached object for "
                        + cacheKey
                        + " is not of type "
                        + type.getName());
            }
            return type.cast(cached);
        }
        return null;
    }

    public <T> void put(CacheKey cacheKey, T cacheValue) {
        if (cacheKey == null) {
            throw new IllegalArgumentException("Parameter 'cacheKey' must be not null");
        }
        if (cacheValue == null) {
            throw new IllegalArgumentException("Parameter 'cacheValue' must be not null");
        }
        this.data.put(cacheKey, cacheValue);
    }

}
