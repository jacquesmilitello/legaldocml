package io.legaldocml.akn;

import io.legaldocml.akn.element.Alinea;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggerInstancePostProcessor.class)
public class AknElementsTest {

    @Test
    public void testExists() {
        assertTrue(AknElements.exists("alinea"));
        assertTrue(AknElements.exists(new Amendment().name()));
        assertFalse(AknElements.exists("toto"));
    }

    @Test
    public void testClass() {
        assertEquals(Alinea.class, AknElements.getAknClass("alinea"));
        assertEquals(Amendment.class, AknElements.getAknClass(new Amendment().name()));
    }
}
