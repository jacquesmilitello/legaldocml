package io.legaldocml.akn;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknElementsTest {

    @Test
    void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(AknElements.class);
    }

    @Test
    void testExists() {
        assertTrue(AknElements.exists("alinea"));
        assertTrue(AknElements.exists(new Amendment().name()));
        assertFalse(AknElements.exists("toto"));
    }

    @Test
    void testClass() {
        assertEquals(Alinea.class, AknElements.getAknClass("alinea"));
        assertEquals(Amendment.class, AknElements.getAknClass(new Amendment().name()));
    }
}
