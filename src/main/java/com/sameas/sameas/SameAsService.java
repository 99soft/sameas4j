package com.sameas.sameas;

import com.sameas.sameas.core.Equivalence;

import java.net.URI;

/**
 * It models the methods that a <a href="http://sameas.org">sameAs.org</a>
 * client must expose.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
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
