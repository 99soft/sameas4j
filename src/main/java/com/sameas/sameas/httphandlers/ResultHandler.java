package com.sameas.sameas.httphandlers;

import java.io.IOException;
import java.io.InputStreamReader;
import com.sameas.sameas.core.Equivalence;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import com.sameas.sameas.json.ResultAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <code>HTTP handler</code> able to parse the <a href="http://sameas.org">sameas.org</a>
 * response and build a not null {@link com.sameas.sameas.core.Equivalence} instance.
 */
public class ResultHandler implements ResponseHandler<Equivalence> {

    private Gson gson;

    public ResultHandler() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Equivalence.class, new ResultAdapter());
        this.gson = gsonBuilder.create();
    }

    public Equivalence handleResponse(HttpResponse response)
            throws IOException {
        return this.gson.fromJson(new InputStreamReader(response.getEntity().getContent()), 
                Equivalence.class);
    }

}
