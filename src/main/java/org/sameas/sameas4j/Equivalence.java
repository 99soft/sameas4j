package org.sameas.sameas4j;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class models an equivalence. An equivalence is a response given by
 * <a href="http://sameas.org">sameas.org</a> to a given <code>URI</code> in
 * terms of other <code>owl:sameAs</code> <code>URI</code>.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public final class Equivalence implements Iterable<URI> {

    /**
     * The equivalent URIs.
     */
    private final Set<URI> duplicates = new HashSet<URI>();

    /**
     * The requested URI.
     */
    private final URI uri;

    /**
     * Creates a new {@code Equivalence} instance by the requested URI.
     *
     * @param uri the requested URI.
     */
    protected Equivalence(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the requested URI.
     *
     * @return the requested URI.
     */
    public URI getUri() {
        return this.uri;
    }

    /**
     * Returns the number of equivalent URIs.
     *
     * @return the number of equivalent URIs.
     */
    public int getAmount() {
        return this.duplicates.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<URI> iterator() {
        return this.duplicates.iterator();
    }

    /**
     * Adds an equivalent URI.
     *
     * @param uri an equivalent URI.
     */
    protected void addDuplicate(URI uri) {
        this.duplicates.add(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("Equivalence { uri: %s, amount: %s, duplicates: %s }",
                this.uri, this.getAmount(), this.duplicates);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        return this.uri.equals(((Equivalence) object).getUri()) ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.uri.hashCode();
    }

}
