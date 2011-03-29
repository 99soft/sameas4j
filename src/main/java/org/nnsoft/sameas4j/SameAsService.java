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

import org.nnsoft.sameas4j.cache.Cache;

/**
 * It models the methods that a <a href="http://sameas.org">sameas.org</a>
 * client must expose.
 */
public interface SameAsService {

    /**
     * It returns a set of <code>URI</code> identifying equivalent
     * resources to the input <code>URI</code>.
     *
     * @param uri the URI for which the equivalence has to be known.
     * @return the URI equivalence.
     * @throws SameAsServiceException if an HTTP or JSON parse error occurs.
     */
    Equivalence getDuplicates(URI uri) throws SameAsServiceException;

    /**
     * It returns a list of {@link org.nnsoft.sameas4j.Equivalence} objects
     * identifying equivalent <code>URI<code> for every possible co-reference
     * match.
     *
     * See also the official <a href="http://sameas.org/about.php">SameAs.org documentation</a>.
     *
     * @param keyword the keyword for which the equivalence has to be known.
     * @return a list of possible equivalences.
     * @throws SameAsServiceException if an HTTP or JSON parse error occurs.
     */
    EquivalenceList getDuplicates(String keyword) throws SameAsServiceException;

    /**
     * 
     * @param cache
     */
    void setCache(Cache cache);

}
