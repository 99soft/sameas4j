package org.sameas.sameas4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Default implementation of {@link org.sameas.sameas4j.SameAsService}.
 *
 * @version $Id$
 */
public final class EquivalenceList implements Iterable<Equivalence> {

    private final List<Equivalence> equivalenceList = new ArrayList<Equivalence>();

    protected void addEquivalence(Equivalence equivalence) {
        this.equivalenceList.add(equivalence);
    }

    @Override
    public Iterator<Equivalence> iterator() {
        return equivalenceList.iterator();
    }

    public int size() {
        return this.equivalenceList.size();
    }

    @Override
    public String toString() {
        return this.equivalenceList.toString();
    }

}
