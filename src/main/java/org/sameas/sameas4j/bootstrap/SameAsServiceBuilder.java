package org.sameas.sameas4j.bootstrap;

import org.nnsoft.sameas4j.DefaultSameAsServiceFactory;
import org.nnsoft.sameas4j.SameAsService;

/**
 * 
 * @since 1.2
 * @version $Id$
 */
public final class SameAsServiceBuilder {

    public CacheBuilder createNew() {
        return this.newCacheBuilder(DefaultSameAsServiceFactory.createNew());
    }

    public CacheBuilder getSingletonInstance() {
        return this.newCacheBuilder(DefaultSameAsServiceFactory.getSingletonInstance());
    }

    private CacheBuilder newCacheBuilder(SameAsService sameAsService) {
        return new CacheBuilder(sameAsService);
    }

}
