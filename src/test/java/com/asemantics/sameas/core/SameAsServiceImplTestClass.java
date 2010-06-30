package com.asemantics.sameas.core;

import java.net.URI;
import java.net.URISyntaxException;

import com.asemantics.sameas.DefaultSameAsServiceFactory;
import com.asemantics.sameas.SameAsServiceException;
import com.asemantics.sameas.SameAsService;
import junit.framework.TestCase;

/**
 * Reference test class for {@link com.asemantics.sameas.SameAsServiceImpl}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class SameAsServiceImplTestClass extends TestCase {

    private SameAsService service = null;

    @Override
    protected void setUp() throws Exception {
        this.service = DefaultSameAsServiceFactory
            .getService(SameAsService.class);
    }

    @Override
    protected void tearDown() throws Exception {
        this.service = null;
    }

    public void testGetDuplicates() throws URISyntaxException, SameAsServiceException {
        Equivalence equivalence;
        equivalence = service
                .getDuplicates(new URI("http://www.bbc.co.uk/music/" +
                        "artists/e9dfc148-d5f6-425e-b80b-f99ed2bd7c09"));
        assertNotNull(equivalence);
        assertTrue(equivalence.getAmount() == 10);
    }

}
