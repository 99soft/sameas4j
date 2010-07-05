package org.sameas.sameas4j;

import java.net.URI;

import org.sameas.sameas4j.core.Equivalence;

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
     * @param uri
     * @return
     * @throws SameAsServiceException
     */
    public Equivalence getDuplicates(URI uri) throws SameAsServiceException;

}
