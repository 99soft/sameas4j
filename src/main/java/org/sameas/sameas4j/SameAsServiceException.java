package org.sameas.sameas4j;

/**
 * Raised when an error occours within {@link org.sameas.sameas4j.SameAsService}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public final class SameAsServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public SameAsServiceException(String message) {
        super(message);
    }

    public SameAsServiceException(String message, Exception e) {
        super(message, e);
    }

}
