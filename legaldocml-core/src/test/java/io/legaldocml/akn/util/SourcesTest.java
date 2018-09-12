package io.legaldocml.akn.util;

import io.legaldocml.akn.AknAttributes;
import io.legaldocml.akn.element.Proprietary;
import io.legaldocml.akn.exception.MandatoryAttributeException;
import io.legaldocml.akn.exception.MetaException;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
class SourcesTest {

    @Test
    void testAddMissingSource() {

        AknList<Proprietary> proprietaries = new AknList<>(new Proprietary[2]);
        Proprietary proprietary = new Proprietary();

        MandatoryAttributeException ex = assertThrows(MandatoryAttributeException.class, () -> Sources.add(proprietaries, proprietary));
        assertEquals(AknAttributes.SOURCE, ex.getAttribute());
        assertEquals(proprietary, ex.getAknObject());

        proprietary.setSource(AgentRef.EMPTY);
        ex = assertThrows(MandatoryAttributeException.class, () -> Sources.add(proprietaries, proprietary));
        assertEquals(AknAttributes.SOURCE, ex.getAttribute());
        assertEquals(proprietary, ex.getAknObject());
    }

    @Test
    void testAddAlreadyExists() {

        AknList<Proprietary> proprietaries = new AknList<>(new Proprietary[2]);
        Proprietary proprietary = new Proprietary();
        proprietary.setSource(AgentRef.valueOf("test"));

        Sources.add(proprietaries, proprietary);
        assertThrows(MetaException.class, () -> Sources.add(proprietaries, proprietary));

    }

    @Test
    void testAddNullParameters() {
        assertThrows(NullPointerException.class, () -> Sources.add(null, new Proprietary()));
        assertThrows(NullPointerException.class, () -> Sources.add(new AknList<>(new Proprietary[1]), null));
    }

    @Test
    void testGetNullParameters() {
        assertThrows(NullPointerException.class, () -> Sources.get(null, null));
        assertNull(Sources.get(null, AgentRef.valueOf("test")));
    }

    @Test
    void testGet() {

        AknList<Proprietary> proprietaries = new AknList<>(new Proprietary[2]);
        Proprietary proprietary = new Proprietary();
        proprietary.setSource(AgentRef.valueOf("test"));
        Sources.add(proprietaries, proprietary);

        assertEquals(proprietary, Sources.get(proprietaries, AgentRef.valueOf("test")));
        assertNull(Sources.get(proprietaries, AgentRef.valueOf("TeSt")));
    }
}
