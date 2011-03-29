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

/**
 * 
 */
public final class CacheKey {

    private final String service;

    private final long lastModified;

    public CacheKey(String service, long lastModified) {
        if (service == null) {
            throw new IllegalArgumentException("Parameter 'service' must not be null");
        }

        this.service = service;
        this.lastModified = lastModified;
    }

    public String getService() {
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
