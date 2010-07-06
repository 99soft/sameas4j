package org.sameas.sameas4j.core;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class models an equivalence. An equivalence is a response given by
 * <a href="http://sameas.org">sameas.org</a> to a given <code>URI</code> in
 * terms of other <code>owl:sameAs</code> <code>URI</code>.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public final class Equivalence {

    private final Set<URI> duplicates = new HashSet<URI>();

    private final URI uri;

    public Equivalence(URI uri) {
        this.uri = uri;
    }

    public URI getUri() {
        return this.uri;
    }

    /**
     * Returns the number of equivalent URIs
     *
     * @return an integer
     */
    public int getAmount() {
        return this.duplicates.size();
    }

    public Iterable<URI> getDuplicates() {
        return Collections.unmodifiableSet(this.duplicates);
    }

    /* TODO this can be made protected moving this class and the adapter on top level */
    public void addDuplicate(URI uri) {
        this.duplicates.add(uri);
    }

    @Override
    public String toString() {
        return String.format("Equivalence { uri: %s, amount: %s, duplicates: %s }",
                this.uri, this.getAmount(), this.duplicates);
    }

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

    @Override
    public int hashCode() {
        return this.uri.hashCode();
    }

}
