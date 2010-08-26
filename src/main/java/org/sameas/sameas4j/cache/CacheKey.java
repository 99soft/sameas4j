package org.sameas.sameas4j.cache;

import java.net.URI;

/**
 * 
 *
 * @version $Id$
 */
public final class CacheKey {

    private final URI service;

    private final long lastModified;

    protected CacheKey(URI service, long lastModified) {
        if (service == null) {
            throw new IllegalArgumentException("Parameter 'service' must not be null");
        }

        this.service = service;
        this.lastModified = lastModified;
    }

    public URI getService() {
        return this.service;
    }

    public long getLastModified() {
        return this.lastModified;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (lastModified ^ (lastModified >>> 32));
        result = prime * result + this.service.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        CacheKey other = (CacheKey) obj;
        if (this.lastModified != other.getLastModified()) {
            return false;
        }
        if (this.service == null) {
            if (other.getService() != null) {
                return false;
            }
        } else if (!this.service.equals(other.getService())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "CacheKey (service="
                + this.service
                + ", lastModified="
                + this.lastModified
                + ")";
    }

}
