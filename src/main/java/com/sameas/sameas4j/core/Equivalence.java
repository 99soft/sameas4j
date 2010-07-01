package com.sameas.sameas4j.core;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models an equivalence. An equivalence is a response given by
 * <a href="http://sameas4j.org">sameas4j.org</a> to a given <code>URI</code> in
 * terms of other <code>owl:sameAs</code> <code>URI</code>.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class Equivalence {

    private URI uri;

    private int amount;

    private List<URI> duplicates = new ArrayList<URI>();

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
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<URI> getDuplicates() {
        return this.duplicates;
    }

    public void setDuplicates(List<URI> duplicates) {
        this.duplicates = duplicates;
        this.amount = this.duplicates.size();
    }

    public void addDuplicate(URI uri) {
        if(!this.duplicates.contains(uri))
            this.duplicates.add(uri);
    }

    @Override
    public String toString() {
        return String.format("uri: %s, amount: %s, duplicates: [%s]",
                this.uri, this.amount, this.duplicates);
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