package io.legaldocml.util;

import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;

import static io.legaldocml.util.CharArrays.immutable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class CharArraysTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(CharArrays.class);
    }

    @Test
    public void testEquals() {

        CharArray array = immutable("hello");

        assertTrue(array.equals("hello"));
        assertEquals(array,immutable("hello"));
        assertEquals(array,array);

        assertFalse(array.equals(null));
        assertFalse(array.equals(Integer.valueOf(25)));

        assertNotEquals(array,immutable("hellO"));
        assertNotEquals(array,immutable("helloo"));
        assertNotEquals(array,"hellO");
        assertNotEquals(array,"helloo");
    }

    @Test
    public void testHashCode() {
        assertEquals(948680436, immutable("hello").hashCode());

        CharBuffer buffer = new CharBuffer();
        buffer.put('h');
        buffer.put('e');
        buffer.put('l');
        buffer.put('l');
        buffer.put('o');
        assertEquals(immutable("hello").hashCode(), buffer.hashCode());


    }

}
