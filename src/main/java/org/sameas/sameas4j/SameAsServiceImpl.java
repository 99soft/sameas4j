package org.sameas.sameas4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

/**
 * Default implementation of {@link org.sameas.sameas4j.SameAsService}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public class SameAsServiceImpl implements SameAsService {

    private static final String SERVICE_URL = "http://sameas.org/json?uri=%s";

    private final GsonBuilder gsonBuilder = new GsonBuilder();

    public SameAsServiceImpl() {
        this.gsonBuilder.registerTypeAdapter(Equivalence.class, new EquivalenceDeserializer());
        // this.gson = gsonBuilder.create();
    }

    public Equivalence getDuplicates(URI uri) throws SameAsServiceException {
        Equivalence equivalence = null;

        String toBeInvoked = String.format(SERVICE_URL, uri);
        URL url = null;
        try {
            url = new URL(toBeInvoked);
        } catch (MalformedURLException e) {
            throw new SameAsServiceException("An error occurred while building the URL '"
                    + toBeInvoked
                    + "'");
        }

        URLConnection connection = null;
        Reader reader = null;
        try {
            connection = url.openConnection();
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).connect();
            }

            reader = new InputStreamReader(connection.getInputStream());
            Gson gson = this.gsonBuilder.create();
            equivalence = gson.fromJson(reader, Equivalence.class);
        } catch (IOException e) {
            throw new SameAsServiceException("An error occurred while invoking the URL '"
                    + toBeInvoked
                    + "'");
        } catch (JsonParseException e) {
            throw new SameAsServiceException("An error occurred while parsing the JSON response", e);
        } finally {
            if (connection != null && connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // close it quietly
                }
            }
        }

        return equivalence;
    }

}
