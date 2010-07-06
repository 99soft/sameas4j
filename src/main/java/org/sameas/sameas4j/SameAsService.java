package org.sameas.sameas4j;

import java.net.URI;

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

}
