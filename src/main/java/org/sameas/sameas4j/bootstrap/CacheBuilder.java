package org.sameas.sameas4j.bootstrap;

import org.nnsoft.sameas4j.SameAsService;
import org.sameas.sameas4j.cache.Cache;
import org.sameas.sameas4j.cache.InMemoryCache;

/**
 * 
 * @since 1.2
 * @version $Id$
 */
public final class CacheBuilder {

    private final SameAsService sameAsService;

    protected CacheBuilder(SameAsService sameAsService) {
        this.sameAsService = sameAsService;
    }

    public SameAsService usingDefaultInMemoryCache() {
        return this.usingCache(new InMemoryCache());
    }

    public SameAsService usingCache(Cache cache) {
        this.sameAsService.setCache(cache);
        return this.sameAsService;
    }

}
