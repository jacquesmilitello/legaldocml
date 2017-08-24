package io.legaldocml.model;

import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import io.legaldocml.test.Tests;
import org.junit.Test;

import static io.legaldocml.model.DefaultModelProvider.INSTANCE;
import static org.junit.Assert.assertEquals;

public class ModelProviderTest {

    @Test
    public void testLanguages() {
        assertEquals(Iso639.FRENCH, INSTANCE.language("fr"));
        assertEquals(Iso639.FRENCH, INSTANCE.language("fra"));
        assertEquals(Iso639.FRENCH, INSTANCE.language("fre"));
        assertEquals("French", INSTANCE.language("fr").getName());

    }

    @Test
    public void testCountries() {
        assertEquals(Iso3166.BELGIUM, INSTANCE.country("be"));
        assertEquals(Iso3166.BELGIUM, INSTANCE.country("bel"));
        assertEquals(Iso3166.BELGIUM.getNumeric(), INSTANCE.country("be").getNumeric());
    }

    @Test
    public void testSingleton() throws Exception {
        Tests.assertSingletonClassIsWellDefined(DefaultModelProvider.class);
    }
}
