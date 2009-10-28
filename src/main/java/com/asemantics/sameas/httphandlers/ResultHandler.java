package com.asemantics.sameas.httphandlers;

import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.asemantics.sameas.Equivalence;
import com.asemantics.sameas.json.ResultAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultHandler implements ResponseHandler<Equivalence> {

    private Gson gson;

    public ResultHandler() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Equivalence.class, new ResultAdapter());
        this.gson = gsonBuilder.create();
    }

    public Equivalence handleResponse(HttpResponse response)
            throws ClientProtocolException, IOException {
        return this.gson.fromJson(new InputStreamReader(response.getEntity().getContent()), 
                Equivalence.class);
    }

}
