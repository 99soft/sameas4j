package com.asemantics.sameas.core;

import java.net.URI;
import java.util.List;

public class Result {

    private URI uri;
    private int amount;
    private List<URI> duplicates;
    public URI getUri() {
        return this.uri;
    }
    public void setUri(URI uri) {
        this.uri = uri;
    }
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
        return this.uri + " " + this.amount + " " + this.duplicates;
    }

}
