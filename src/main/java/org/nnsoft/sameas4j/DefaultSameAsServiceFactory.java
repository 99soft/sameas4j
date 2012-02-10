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

/**
 * Default factory class to build a concrete implementation of {@link org.nnsoft.sameas4j.SameAsService}.
 *
 * @see org.nnsoft.sameas4j.SameAsServiceImpl
 */
public final class DefaultSameAsServiceFactory {

    /**
     * The singleton {@link SameAsService} instance.
     */
    private final static SameAsService INSTANCE = new SameAsServiceImpl();

    /**
     * Hidden constructor, this class can't be instantiated
     */
    private DefaultSameAsServiceFactory() {
        // prevents this class instantiation
    }

    /**
     * Creates a new {@link SameAsService} instance.
     *
     * @return a new {@link SameAsService} instance.
     */
    public static SameAsService createNew() {
        return new SameAsServiceImpl();
    }

    /**
     * Returns the singleton {@link SameAsService} instance.
     *
     * @return the singleton {@link SameAsService} instance.
     */
    public static SameAsService getSingletonInstance() {
        return INSTANCE;
    }

}
