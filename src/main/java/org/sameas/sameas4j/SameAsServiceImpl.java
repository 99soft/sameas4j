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
final class SameAsServiceImpl implements SameAsService {

    /**
     * The sameas.org service for looking up URLs template constant.
     */
    private static final String SERVICE_URL = "http://sameas.org/json?uri=%s";

    /**
     * The sameas.org service for looking up keywords template constant.
     */
    private static final String SERVICE_KEYWORD = "http://sameas.org/json?q=%s";

    /**
     * The GsonBuilder used to create new equivalence JSON parser.
     */
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    /**
     * Creates a new {@link org.sameas.sameas4j.SameAsService} instance.
     */
    public SameAsServiceImpl() {
        this.gsonBuilder.registerTypeAdapter(Equivalence.class, new EquivalenceDeserializer());
        this.gsonBuilder.registerTypeAdapter(EquivalenceList.class, new EquivalenceListDeserializer());
    }

    /**
     * {@inheritDoc}
     */
    public Equivalence getDuplicates(URI uri) throws SameAsServiceException {
        return invokeULR(String.format(SERVICE_URL, uri), Equivalence.class);
    }

    /**
     * {@inheritDoc}
     */
    public EquivalenceList getDuplicates(String keyword) throws SameAsServiceException {
        return invokeULR(String.format(SERVICE_KEYWORD, keyword), EquivalenceList.class);
    }

    /**
     * Invokes a Sameas.org service URL and parses the JSON response.
     *
     * @param <T> the expected return type.
     * @param toBeInvoked the service URL has to be invoked.
     * @param returnType the type the JSON response has to be bind to.
     * @return the bound object.
     * @throws SameAsServiceException is any error occurs.
     */
    private <T> T invokeULR(String toBeInvoked, Class<T> returnType) throws SameAsServiceException {
        URL url;
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
            return gson.fromJson(reader, returnType);
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
    }

}
