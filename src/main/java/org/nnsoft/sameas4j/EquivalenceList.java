/*
 * Copyright (c) 2009-2012 The 99 Software Foundation
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an {@link Equivalence} list.
 */
public final class EquivalenceList implements Iterable<Equivalence> {

    /**
     * The {@link Equivalence} list.
     */
    private final List<Equivalence> equivalenceList = new ArrayList<Equivalence>();

    /**
     * Add a new {@link Equivalence} into the list.
     *
     * @param equivalence the {@link Equivalence} has to be added into the list.
     */
    protected void addEquivalence(Equivalence equivalence) {
        this.equivalenceList.add(equivalence);
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Equivalence> iterator() {
        return equivalenceList.iterator();
    }

    /**
     * Return the number of {@link Equivalence} found.
     *
     * @return the number of {@link Equivalence} found.
     */
    public int size() {
        return this.equivalenceList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.equivalenceList.toString();
    }

}
