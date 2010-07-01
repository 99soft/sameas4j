package com.sameas.sameas4j;

/**
 * Raised when an error occours within {@link com.sameas.sameas4j.SameAsService}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class SameAsServiceException extends Exception {

    public SameAsServiceException(String message) {
        super(message);
    }    

    public SameAsServiceException(String message, Exception e) {
        super(message, e);
    }

}
