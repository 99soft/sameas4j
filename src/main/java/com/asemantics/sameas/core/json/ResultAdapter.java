package com.asemantics.sameas.core.json;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import com.asemantics.sameas.core.Result;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ResultAdapter implements JsonSerializer<Result>, 
    JsonDeserializer<Result> {

    public JsonElement serialize(Result arg0, Type arg1,
            JsonSerializationContext arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    public Result deserialize(JsonElement json, Type type,
            JsonDeserializationContext context) throws JsonParseException {
        
        Result result = new Result();
        
        try {
            result.setUri(new URI(json.getAsJsonArray().get(0)
                    .getAsJsonObject().getAsJsonPrimitive("uri").getAsString()));
            
            result.setAmount(json.getAsJsonArray().get(0)
                    .getAsJsonObject().getAsJsonPrimitive("numDuplicates").getAsInt());
            
            JsonArray duplicates = json.getAsJsonArray().get(0)
            .getAsJsonObject().getAsJsonArray("duplicates");
            
            List<URI> duplicatesURIs = new LinkedList<URI>();
            
            for(int i=0; i<duplicates.size(); i++) {
                URI uri = new URI(duplicates.get(i).getAsString());
                duplicatesURIs.add(uri);
                result.setDuplicates(duplicatesURIs);
            }
            
            
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        return result;
    }

}
