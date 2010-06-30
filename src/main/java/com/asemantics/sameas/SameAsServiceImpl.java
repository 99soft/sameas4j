package com.asemantics.sameas;

import java.io.IOException;
import java.net.URI;

import com.asemantics.sameas.core.Equivalence;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.VersionInfo;
import com.asemantics.sameas.httphandlers.ResultHandler;

/**
 * Default implementation of {@link com.asemantics.sameas.SameAsService}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * 
 */
public class SameAsServiceImpl implements SameAsService {

    private DefaultHttpClient httpclient = null;

    protected SameAsServiceImpl() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(
                new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(
                new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, 
                HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, 
                HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, 
                true);

        // determine the release version from packaged version info
        final VersionInfo vi = VersionInfo.loadVersionInfo
            ("org.apache.http.client", getClass().getClassLoader());
        final String release = (vi != null) ?
            vi.getRelease() : VersionInfo.UNAVAILABLE;
        HttpProtocolParams.setUserAgent(params, 
                "Apache-HttpClient/" + release + " (java 1.6)");

        ThreadSafeClientConnManager connectionManager =
            new ThreadSafeClientConnManager(params, registry);

        this.httpclient = new DefaultHttpClient(connectionManager, null);
    }

    public Equivalence getDuplicates(URI uri) throws SameAsServiceException {
        Equivalence equivalence;
        HttpGet method = new HttpGet("http://sameas.org/json?" +
                "uri=" + uri.toString());
        try {
            equivalence = httpclient.execute(method, new ResultHandler());
        } catch (ClientProtocolException e) {
            throw new SameAsServiceException("error calling the http://sameas.org service", e);
        } catch (IOException e) {
            throw new SameAsServiceException("error calling the http://sameas.org service", e);
        }
        return equivalence;
    }

}
