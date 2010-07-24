package org.sameas.sameas4j;

import java.net.URI;
import java.util.List;

/**
 * It models the methods that a <a href="http://sameas.org">sameas.org</a>
 * client must expose.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public interface SameAsService {

    /**
     * It returns a set of <code>URI</code> identifying equivalent
     * resources to the input <code>URI</code>.
     *
     * @param uri the URI for which the equivalence has to be known.
     * @return the URI equivalence.
     * @throws SameAsServiceException if an HTTP or JSON parse error occurs.
     */
    Equivalence getDuplicates(URI uri) throws SameAsServiceException;

    /**
     * It returns a list of {@link org.sameas.sameas4j.Equivalence} objects
     * identifying equivalent <code>URI<code> for every possible co-reference
     * match.
     *
     * See also the official <a href="http://sameas.org/about.php">SameAs.org documentation</a>.
     *  
     * @param keyword the keyword for which the equivalence has to be known.
     * @return a list of possible equivalences.
     * @throws SameAsServiceException if an HTTP or JSON parse error occurs.
     */
    List<Equivalence> getDuplicates(String keyword) throws SameAsServiceException;

}