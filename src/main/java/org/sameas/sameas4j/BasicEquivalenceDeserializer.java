package org.sameas.sameas4j;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Basic Json adapter that given a JsonElement builds a
 * not null an {@link Equivalence} instance.
 *
 * @version $Id$
 */
final class BasicEquivalenceDeserializer {

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
     * Deserialize a single {@link org.sameas.sameas4j.Equivalence}
     * from its Json serialization.
     * 
     * @param json object to be deserialized
     * @return a not null {@link org.sameas.sameas4j.Equivalence} instance
     */
    public Equivalence getEquivalence(JsonElement json) {
        Equivalence equivalence;
        String uriString = json.getAsJsonObject().getAsJsonPrimitive(URI).getAsString();

        String uri = null;
        try {
            uri = uriString.substring(1, uriString.length() - 1);
            equivalence = new Equivalence(new URI(uri));
        } catch (URISyntaxException e) {
            throw new JsonParseException(String.format(EXCEPTION_MESSAGE, uri));
        }

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
