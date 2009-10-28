package com.asemantics.sameas.core;

import java.net.URI;
import java.net.URISyntaxException;
import com.asemantics.sameas.Equivalence;
import com.asemantics.sameas.SameAsServiceException;
import com.asemantics.sameas.SameAsServiceFactory;
import com.asemantics.sameas.interfaces.SameAsService;
import junit.framework.TestCase;

public class SameAsServiceImplTestClass extends TestCase {

    private SameAsService service = null;

    @Override
    protected void setUp() throws Exception {
        this.service = SameAsServiceFactory
            .getService(SameAsService.class);
    }

    @Override
    protected void tearDown() throws Exception {
        this.service = null;
    }

    public void testGetDuplicates() throws URISyntaxException {

        Equivalence equivalence = null;

        try {
            equivalence = service
                .getDuplicates(new URI("http://www.bbc.co.uk/music/" +
                        "artists/e9dfc148-d5f6-425e-b80b-f99ed2bd7c09"));
        } catch (SameAsServiceException e) {
            fail(e.getMessage());
        }

        System.out.println(equivalence);

        assertNotNull(equivalence);

    }

}
