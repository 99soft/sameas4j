package org.sameas.sameas4j.json;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.sameas.sameas4j.core.Equivalence;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Json adapter that given a JsonElement builds a
 * not null an {@link org.sameas.sameas4j.core.Equivalence} instance.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class ResultAdapter implements JsonSerializer<Equivalence>, 
    JsonDeserializer<Equivalence> {

    public JsonElement serialize(Equivalence arg0, Type arg1,
            JsonSerializationContext arg2) {
        // do nothing
        return null;
    }

    public Equivalence deserialize(JsonElement json, Type type,
                                   JsonDeserializationContext context) throws JsonParseException {
        Equivalence equivalence = new Equivalence();
        String uriString = json.getAsJsonArray().get(0)
                .getAsJsonObject().getAsJsonPrimitive("uri").getAsString();

        String uri = null;
        try {
            uri = uriString.substring(1, uriString.length() - 1);
            equivalence.setUri(new URI(uri));
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("URI %s seems to be not well-formed", uri));
        }

        equivalence.setAmount(json.getAsJsonArray().get(0)
                .getAsJsonObject().getAsJsonPrimitive("numDuplicates").getAsInt());

        JsonArray duplicates = json.getAsJsonArray().get(0)
                .getAsJsonObject().getAsJsonArray("duplicates");

        List<URI> duplicatesURIs = new LinkedList<URI>();

        for (int i = 0; i < duplicates.size(); i++) {
            URI equivalentUri;
            try {
                equivalentUri = new URI(duplicates.get(i).getAsString());
                duplicatesURIs.add(equivalentUri);
            } catch (URISyntaxException e) {
                // if an equivalent URI is not well-formed it's better to do not add it, let's go on
                continue;
            }
        }
        equivalence.setDuplicates(duplicatesURIs);
        return equivalence;
    }

}
