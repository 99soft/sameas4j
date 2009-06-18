package com.asemantics.sameas.core;

import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;

public class SameAsServiceImplTestClass extends TestCase {

    private SameAsServiceImpl service = null;

    public SameAsServiceImplTestClass() {
        this.service = SameAsServiceImpl.getInstance();
    }

    public void testGetDuplicates() throws URISyntaxException {
        System.out.println(service
                .getDuplicates(new URI("http://dbpedia.org/resource/London")));
    }

}
