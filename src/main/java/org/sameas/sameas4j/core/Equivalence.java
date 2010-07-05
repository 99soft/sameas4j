package org.sameas.sameas4j.core;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class models an equivalence. An equivalence is a response given by
 * <a href="http://sameas4j.org">sameas4j.org</a> to a given <code>URI</code> in
 * terms of other <code>owl:sameAs</code> <code>URI</code>.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public class Equivalence {

    private URI uri;

    private final Set<URI> duplicates = new HashSet<URI>();

    public URI getUri() {
        return this.uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the number of equivalent URIs
     *
     * @return an integer
     */
    public int getAmount() {
        return this.duplicates.size();
    }

    public Set<URI> getDuplicates() {
        return Collections.unmodifiableSet(this.duplicates);
    }

    public void addDuplicate(URI uri) {
        this.duplicates.add(uri);
    }

    @Override
    public String toString() {
        return String.format("uri: %s, amount: %s, duplicates: %s",
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