package org.nnsoft.sameas4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nnsoft.sameas4j.bootstrap.SameAsServiceBuilder;

/**
 * Reference test class for {@link org.nnsoft.sameas4j.SameAsServiceImpl}.
 */
public final class SameAsServiceImplTestCase {

    private SameAsService service = null;

    @Before
    public void setUp() throws Exception {
        this.service = new SameAsServiceBuilder().createNew().usingDefaultInMemoryCache();
    }

    @After
    public void tearDown() throws Exception {
        this.service = null;
    }

    @Test
    public void fetDuplicatesFromURI() throws SameAsServiceException {
        Equivalence equivalence = this.service
                .getDuplicates(URI.create("http://www.bbc.co.uk/music/artists/e9dfc148-d5f6-425e-b80b-f99ed2bd7c09"));
        assertNotNull(equivalence);
        assertEquals(10, equivalence.getAmount());
    }

    @Test
    public void getDuplicatesFromKeyword() throws SameAsServiceException {
        EquivalenceList equivalences = this.service.getDuplicates("Rome");
        assertEquals(10, equivalences.size());
    }

}
