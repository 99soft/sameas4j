package org.nnsoft.sameas4j;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Json adapter that given a JsonElement builds a
 * not null an {@link org.nnsoft.sameas4j.Equivalence} instance.
 */
final class EquivalenceDeserializer
        extends AbstractEquivalenceDeserializer
        implements JsonDeserializer<Equivalence> {

    /**
     * {@inheritDoc}
     */
    public Equivalence deserialize(JsonElement json,
            Type type,
            JsonDeserializationContext context) throws JsonParseException {
        return this.getEquivalence(json.getAsJsonArray().get(0));
    }

}
