package org.sameas.sameas4j;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Json adapter that given a JsonElement builds a
 * list of {@link Equivalence} instance.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
final class EquivalenceListDeserializer
        extends AbstractEquivalenceDeserializer
        implements JsonDeserializer<EquivalenceList> {

    /**
     * {@inheritDoc}
     */
    public EquivalenceList deserialize(JsonElement json,
            Type type,
            JsonDeserializationContext context) throws JsonParseException {
        EquivalenceList equivalenceList = new EquivalenceList();

        JsonArray jsonArrayOfEquivalences = json.getAsJsonArray();
        for (int i = 0; i < jsonArrayOfEquivalences.size(); i++) {
            equivalenceList.addEquivalence(
                    this.getEquivalence(jsonArrayOfEquivalences.get(i)));
        }

        return equivalenceList;
    }

}
