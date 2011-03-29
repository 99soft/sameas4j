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

import static java.lang.String.format;

/**
 * The {@link Cache} key representation.
 */
public final class CacheKey {

    /**
     * The sameas service.
     */
    private final String service;

    /**
     * The sameas service "Last-modified" header.
     */
    private final long lastModified;

    /**
     * Creates a new {@code Cache} key instance.
     *
     * @param service The sameas service
     * @param lastModified The sameas service "Last-modified" header
     */
    public CacheKey(String service, long lastModified) {
        if (service == null) {
            throw new IllegalArgumentException("Parameter 'service' must not be null");
        }

        this.service = service;
        this.lastModified = lastModified;
    }

    /**
     * Returns the sameas service.
     *
     * @return The sameas service
     */
    public String getService() {
        return this.service;
    }

    /**
     * Returns the sameas service "Last-modified" header.
     *
     * @return The sameas service "Last-modified" header
     */
    public long getLastModified() {
        return this.lastModified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (lastModified ^ (lastModified >>> 32));
        result = prime * result + this.service.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     */
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

     /**
      * {@inheritDoc}
      */
     @Override
    public String toString() {
        return format("CacheKey (service=%s, lastModified=%s)", service, lastModified);
    }

}
