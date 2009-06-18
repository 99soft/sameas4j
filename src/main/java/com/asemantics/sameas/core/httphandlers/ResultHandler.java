package com.asemantics.sameas.core.httphandlers;

import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.asemantics.sameas.core.Result;
import com.asemantics.sameas.core.json.ResultAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultHandler implements ResponseHandler<Result> {

    private Gson gson;

    public ResultHandler() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Result.class, new ResultAdapter());
        this.gson = gsonBuilder.create();
    }

    public Result handleResponse(HttpResponse response)
            throws ClientProtocolException, IOException {
        return this.gson.fromJson(new InputStreamReader(response.getEntity().getContent()), 
                Result.class);
    }

}
