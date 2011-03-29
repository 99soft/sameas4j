package org.nnsoft.sameas4j;

/**
 * Raised when an error occurs within {@link org.nnsoft.sameas4j.SameAsService}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
 */
public final class SameAsServiceException extends Exception {

    /**
     * The default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new SameAsServiceException with the specified detail message.
     *
     * @param message the specified detail message.
     */
    public SameAsServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the specified detail message.
     * @param e the specified detail cause.
     */
    public SameAsServiceException(String message, Exception e) {
        super(message, e);
    }

}
