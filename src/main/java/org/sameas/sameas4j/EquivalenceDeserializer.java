package org.sameas.sameas4j;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Json adapter that given a JsonElement builds a
 * not null an {@link org.sameas.sameas4j.Equivalence} instance.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
final class EquivalenceDeserializer implements JsonDeserializer<Equivalence> {

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
     * {@inheritDoc}
     */
    public Equivalence deserialize(JsonElement json,
            Type type,
            JsonDeserializationContext context) throws JsonParseException {
        Equivalence equivalence = null;
        String uriString = json.getAsJsonArray().get(0)
                .getAsJsonObject().getAsJsonPrimitive(URI).getAsString();

        String uri = null;
        try {
            uri = uriString.substring(1, uriString.length() - 1);
            equivalence = new Equivalence(new URI(uri));
        } catch (URISyntaxException e) {
            throw new JsonParseException(String.format(EXCEPTION_MESSAGE, uri));
        }

        JsonArray duplicates = json.getAsJsonArray().get(0)
                .getAsJsonObject().getAsJsonArray(DUPLICATES);

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