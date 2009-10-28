package com.asemantics.sameas;

public class SameAsServiceException extends Exception {

    private static final long serialVersionUID = -4895283012278522481L;

    public SameAsServiceException(String message, Exception e) {
        super(message, e);
    }

    public SameAsServiceException(String message) {
        super(message);
    }

    public SameAsServiceException(Exception e) {
        super(e);
    }

}
