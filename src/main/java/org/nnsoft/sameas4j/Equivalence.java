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
package org.nnsoft.sameas4j;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class models an equivalence. An equivalence is a response given by
 * <a href="http://sameas.org">sameas.org</a> to a given <code>URI</code> in
 * terms of other <code>owl:sameAs</code> <code>URI</code>.
 */
public final class Equivalence implements Iterable<URI> {

    /**
     * The equivalent URIs.
     */
    private final Set<URI> duplicates = new HashSet<URI>();

    /**
     * The requested URI.
     */
    private final URI uri;

    /**
     * Creates a new {@code Equivalence} instance by the requested URI.
     *
     * @param uri the requested URI.
     */
    protected Equivalence(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the requested URI.
     *
     * @return the requested URI.
     */
    public URI getUri() {
        return this.uri;
    }

    /**
     * Returns the number of equivalent URIs.
     *
     * @return the number of equivalent URIs.
     */
    public int getAmount() {
        return this.duplicates.size();
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<URI> iterator() {
        return this.duplicates.iterator();
    }

    /**
     * Adds an equivalent URI.
     *
     * @param uri an equivalent URI.
     */
    protected void addDuplicate(URI uri) {
        this.duplicates.add(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("Equivalence { uri: %s, amount: %s, duplicates: %s }",
                this.uri, this.getAmount(), this.duplicates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        return this.uri.equals(((Equivalence) object).getUri()) ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.uri.hashCode();
    }

}
