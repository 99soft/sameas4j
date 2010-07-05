package org.sameas.sameas4j;

import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.TestCase;

import org.sameas.sameas4j.core.Equivalence;

/**
 * Reference test class for {@link org.sameas.sameas4j.SameAsServiceImpl}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 *
 */
public class SameAsServiceImplTestClass extends TestCase {

    private SameAsService service = null;

    @Override
    protected void setUp() throws Exception {
        this.service = DefaultSameAsServiceFactory.getSingletonInstance();
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
