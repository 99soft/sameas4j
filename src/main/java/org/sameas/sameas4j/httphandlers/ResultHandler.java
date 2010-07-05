package org.sameas.sameas4j.httphandlers;

import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.sameas.sameas4j.core.Equivalence;
import org.sameas.sameas4j.json.EquivalenceResultAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <code>HTTP handler</code> able to parse the <a href="http://sameas4j.org">sameas4j.org</a>
 * response and build a not null {@link org.sameas.sameas4j.core.Equivalence} instance.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public class ResultHandler implements ResponseHandler<Equivalence> {

    private Gson gson;

    public ResultHandler() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Equivalence.class, new EquivalenceResultAdapter());
        this.gson = gsonBuilder.create();
    }

    public Equivalence handleResponse(HttpResponse response)
            throws IOException {
        return this.gson.fromJson(new InputStreamReader(response.getEntity().getContent()), 
                Equivalence.class);
    }

}
