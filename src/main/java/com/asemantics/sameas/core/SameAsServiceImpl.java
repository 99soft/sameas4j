package com.asemantics.sameas.core;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.asemantics.sameas.core.httphandlers.ResultHandler;

public class SameAsServiceImpl implements SameAsService {
    
    private static SameAsServiceImpl instance = null;
    
    private DefaultHttpClient httpclient = null;
    
    public static SameAsServiceImpl getInstance() {
        if(instance == null)
            instance = new SameAsServiceImpl();
        return instance;
    }
    
    private SameAsServiceImpl() {
        this.httpclient = new DefaultHttpClient();
    }
    
    public Result getDuplicates(URI uri) {
        
        Result result = new Result();
        HttpGet method = new HttpGet("http://sameas.org/json?" +
                "uri=" + uri.toString());

        try {
            result = httpclient.execute(method, new ResultHandler());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }

}
