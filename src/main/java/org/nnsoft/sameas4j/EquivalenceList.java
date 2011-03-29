package org.nnsoft.sameas4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an {@link Equivalence} list.
 */
public final class EquivalenceList implements Iterable<Equivalence> {

    /**
     * The {@link Equivalence} list.
     */
    private final List<Equivalence> equivalenceList = new ArrayList<Equivalence>();

    /**
     * Add a new {@link Equivalence} into the list.
     *
     * @param equivalence the {@link Equivalence} has to be added into the list.
     */
    protected void addEquivalence(Equivalence equivalence) {
        this.equivalenceList.add(equivalence);
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<Equivalence> iterator() {
        return equivalenceList.iterator();
    }

    /**
     * Return the number of {@link Equivalence} found.
     *
     * @return the number of {@link Equivalence} found.
     */
    public int size() {
        return this.equivalenceList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.equivalenceList.toString();
    }

}
