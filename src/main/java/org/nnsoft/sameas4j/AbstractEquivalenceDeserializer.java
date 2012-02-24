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

import java.io.UnsupportedEncodingException;
import java.net.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Basic Json adapter that given a JsonElement builds a
 * not null an {@link Equivalence} instance.
 */
abstract class AbstractEquivalenceDeserializer {

    /**
     * The {@code uri} constant.
     */
    private static final String URI = "uri";

    /**
     * The {@code duplicates} constant.
     */
    private static final String DUPLICATES = "duplicates";

    /**
     * The error message template constant.
     */
    private static final String EXCEPTION_MESSAGE = "URI '%s' seems to be not well-formed";

    /**
     * Deserialize a single {@link org.nnsoft.sameas4j.Equivalence}
     * from its Json serialization.
     * 
     * @param json object to be deserialized
     * @return a not null {@link org.nnsoft.sameas4j.Equivalence} instance
     */
    public Equivalence getEquivalence(JsonElement json) {
        Equivalence equivalence;
        String uriString = json.getAsJsonObject().getAsJsonPrimitive(URI).getAsString();
        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            throw new JsonParseException(String.format(EXCEPTION_MESSAGE, uriString));
        }
        equivalence = new Equivalence(uri);
        JsonArray duplicates = json.getAsJsonObject().getAsJsonArray(DUPLICATES);
        for (int i = 0; i < duplicates.size(); i++) {
            try {
                equivalence.addDuplicate(new URI(duplicates.get(i).getAsString()));
            } catch (URISyntaxException e) {
                // if an equivalent URI is not well-formed it's better to do not add it, let's go on
                continue;
            }
        }
        return equivalence;
    }

}
