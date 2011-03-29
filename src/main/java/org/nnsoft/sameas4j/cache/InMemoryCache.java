/*
 * Copyright (c) 2009-2011 The 99 Software Foundation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.nnsoft.sameas4j.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An in-memory based {@code Cache} implementation.
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

    /**
     * {@inheritDoc}
     */
    public <T> T get(CacheKey cacheKey) {
        if (cacheKey == null) {
            throw new IllegalArgumentException("Parameter 'cacheKey' must be not null");
        }

        Object cached = this.data.get(cacheKey);
        if (cached != null) {
            @SuppressWarnings("unchecked")
            T returned = (T) cached;
            return returned;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
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
