package org.sameas.sameas4j;

import java.net.URI;

import junit.framework.TestCase;


/**
 * Reference test class for {@link org.sameas.sameas4j.SameAsServiceImpl}.
 *
 * @author Davide Palmisano (dpalmisano@gmail.com)
 * @version $Id$
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

    public void testGetDuplicates() throws SameAsServiceException {
        Equivalence equivalence;
        equivalence = this.service
                .getDuplicates(URI.create("http://www.bbc.co.uk/music/artists/e9dfc148-d5f6-425e-b80b-f99ed2bd7c09"));
        assertNotNull(equivalence);
        assertEquals(10, equivalence.getAmount());
    }

}
