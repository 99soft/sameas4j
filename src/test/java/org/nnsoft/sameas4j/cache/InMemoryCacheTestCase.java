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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nnsoft.sameas4j.Equivalence;

/**
 * 
 */
public final class InMemoryCacheTestCase {

    private CacheKey cacheKey;

    private Cache cache;

    @Before
    public void setUp() throws Exception {
        this.cacheKey = new CacheKey("mockService", 123);
        this.cache = new InMemoryCache();
    }

    @After
    public void tearDown() throws Exception {
        this.cacheKey = null;
        this.cache = null;
    }

    @Test
    public void retrieveNull() {
        Equivalence nullEquivalence = cache.get(cacheKey);
        assertNull(nullEquivalence);
    }

    @Test
    public void storeAndRetrieve() {
        final String expected = "stored object";

        cache.put(cacheKey, expected);

        String actual = cache.get(cacheKey);
        assertSame(expected, actual);
    }

}
