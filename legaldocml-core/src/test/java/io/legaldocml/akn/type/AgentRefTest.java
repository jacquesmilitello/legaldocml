package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
class AgentRefTest {

    @Test
    void testWithRef() {
        assertEquals("#toto", AgentRef.valueOf("#toto").toString());
        assertEquals("toto", AgentRef.valueOf("toto").toString());
    }


    @Test
    void testBadValueOnValueOf() {
        Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf(null));
        Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf(""));
    }

}
