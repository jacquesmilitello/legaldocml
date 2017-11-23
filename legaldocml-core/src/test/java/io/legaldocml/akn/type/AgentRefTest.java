package io.legaldocml.akn.type;

import io.legaldocml.akn.AttributeValueException;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
public class AgentRefTest {

    @Test
    public void testWithRef() {
        assertEquals("#toto", AgentRef.valueOf("#toto").toString());
        assertEquals("#toto", AgentRef.valueOf("#toto".toCharArray()).toString());
        assertEquals("#toto", AgentRef.raw("#toto".toCharArray()).toString());
    }

    @Test
    public void testWithOutRef() {
        assertEquals("#toto", AgentRef.valueOf("toto").toString());
        assertEquals("#toto", AgentRef.valueOf("toto".toCharArray()).toString());
        assertEquals("toto", AgentRef.raw("toto".toCharArray()).toString());
    }

    @Test
    public void testBadValueOnValueOf01() {
        AttributeValueException exception = Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf((String) null));
        Assertions.assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    public void testBadValueOnValueOf02() {
        AttributeValueException exception = Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf((char[]) null));
        Assertions.assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    public void testBadValueOnValueOf03() {
        AttributeValueException exception = Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf(""));
        Assertions.assertTrue(exception.getMessage().contains("[]"));
    }

    @Test
    public void testBadValueOnValueOf04() {
        AttributeValueException exception = Assertions.assertThrows(AttributeValueException.class, () -> AgentRef.valueOf("".toCharArray()));
        Assertions.assertTrue(exception.getMessage().contains("[]"));
    }
}
