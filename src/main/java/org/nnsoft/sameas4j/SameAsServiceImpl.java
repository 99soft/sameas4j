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

import static java.lang.String.format;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.nnsoft.sameas4j.cache.Cache;
import org.nnsoft.sameas4j.cache.CacheKey;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

/**
 * Default implementation of {@link org.nnsoft.sameas4j.SameAsService}.
 */
final class SameAsServiceImpl implements SameAsService {

    /**
     * The sameas.org service for looking up URLs template constant.
     */
    private static final String SERVICE_URL = "http://sameas.org/json?uri=%s";

    /**
     * The sameas.org service for looking up keywords template constant.
     */
    private static final String SERVICE_KEYWORD = "http://sameas.org/json?q=%s";

    /**
     * The GsonBuilder used to create new equivalence JSON parser.
     */
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    /**
     * The {@code Cache} reference, can be null.
     */
    private Cache cache;

    /**
     * Creates a new {@link org.nnsoft.sameas4j.SameAsService} instance.
     */
    public SameAsServiceImpl() {
        this.gsonBuilder.registerTypeAdapter(Equivalence.class, new EquivalenceDeserializer());
        this.gsonBuilder.registerTypeAdapter(EquivalenceList.class, new EquivalenceListDeserializer());
    }

    /**
     * {@inheritDoc}
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * {@inheritDoc}
     */
    public Equivalence getDuplicates(URI uri) throws SameAsServiceException {
        return invokeULR(format(SERVICE_URL, uri), Equivalence.class);
    }

    /**
     * {@inheritDoc}
     */
    public EquivalenceList getDuplicates(String keyword) throws SameAsServiceException {
        return invokeULR(format(SERVICE_KEYWORD, keyword), EquivalenceList.class);
    }

    /**
     * Invokes a Sameas.org service URL and parses the JSON response.
     *
     * @param <T> the expected return type.
     * @param toBeInvoked the service URL has to be invoked.
     * @param returnType the type the JSON response has to be bind to.
     * @return the bound object.
     * @throws SameAsServiceException is any error occurs.
     */
    private <T> T invokeULR(String toBeInvoked, Class<T> returnType) throws SameAsServiceException {
        URL url;
        try {
            url = new URL(toBeInvoked);
        } catch (MalformedURLException e) {
            throw new SameAsServiceException("An error occurred while building the URL '"
                    + toBeInvoked
                    + "'");
        }

        URLConnection connection = null;
        Reader reader = null;
        try {
            connection = url.openConnection();
            long lastModified = connection.getLastModified();

            if (this.cache != null) {
                CacheKey cacheKey = new CacheKey(toBeInvoked, lastModified);
                T cached = this.cache.get(cacheKey, returnType);
                if (cached != null) {
                    return cached;
                }
            }

            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).connect();
            }

            reader = new InputStreamReader(connection.getInputStream());
            Gson gson = this.gsonBuilder.create();
            T response = gson.fromJson(reader, returnType);

            if (this.cache != null) {
                CacheKey cacheKey = new CacheKey(toBeInvoked, lastModified);
                this.cache.put(cacheKey, response);
            }

            return response;
        } catch (IOException e) {
            throw new SameAsServiceException("An error occurred while invoking the URL '"
                    + toBeInvoked
                    + "'");
        } catch (JsonParseException e) {
            throw new SameAsServiceException("An error occurred while parsing the JSON response", e);
        } finally {
            if (connection != null && connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // close it quietly
                }
            }
        }
    }

}
