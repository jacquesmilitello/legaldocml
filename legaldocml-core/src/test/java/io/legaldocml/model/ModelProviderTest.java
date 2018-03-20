package io.legaldocml.model;

import io.legaldocml.business.BusinessProvider;
import io.legaldocml.iso.Iso3166;
import io.legaldocml.iso.Iso639;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelProviderTest {

    private ModelProvider modelProvider = BusinessProvider.businessProvider("default").modelProvider();

    @Test
    void testLanguages() {
        assertEquals(Iso639.FRENCH, modelProvider.language("fr"));
        assertEquals(Iso639.FRENCH, modelProvider.language("fra"));
        assertEquals(Iso639.FRENCH, modelProvider.language("fre"));
        assertEquals("French", modelProvider.language("fr").getName());

    }

    @Test
    void testCountries() {
        assertEquals(Iso3166.BELGIUM, modelProvider.country("be"));
        assertEquals(Iso3166.BELGIUM, modelProvider.country("bel"));
        assertEquals(Iso3166.BELGIUM.getNumeric(), modelProvider.country("be").getNumeric());
    }

}
