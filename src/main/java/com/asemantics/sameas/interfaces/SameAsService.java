package com.asemantics.sameas.interfaces;

import java.net.URI;

import com.asemantics.sameas.Equivalence;
import com.asemantics.sameas.SameAsServiceException;

public interface SameAsService {

    public Equivalence getDuplicates(URI uri) throws SameAsServiceException;

}
