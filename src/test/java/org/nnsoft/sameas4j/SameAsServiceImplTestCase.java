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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nnsoft.sameas4j.bootstrap.SameAsServiceBuilder;

/**
 * Reference test class for {@link org.nnsoft.sameas4j.SameAsServiceImpl}.
 */
public final class SameAsServiceImplTestCase {

    private SameAsService service = null;

    @Before
    public void setUp() throws Exception {
        this.service = new SameAsServiceBuilder().createNew().usingDefaultInMemoryCache();
    }

    @After
    public void tearDown() throws Exception {
        this.service = null;
    }

    @Test
    public void fetchDuplicatesFromURI() throws SameAsServiceException {
        Equivalence equivalence = this.service
                .getDuplicates(URI.create("http://www.bbc.co.uk/music/artists/e9dfc148-d5f6-425e-b80b-f99ed2bd7c09"));
        assertNotNull(equivalence);
        assertEquals(11, equivalence.getAmount());
    }

    @Test
    public void getDuplicatesFromKeyword() throws SameAsServiceException {
        EquivalenceList equivalences = this.service.getDuplicates("Rome");
        assertEquals(10, equivalences.size());
    }

}
