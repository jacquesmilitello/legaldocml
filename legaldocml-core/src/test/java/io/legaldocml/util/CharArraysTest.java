package io.legaldocml.util;

import org.junit.Assert;
import org.junit.Test;

import static io.legaldocml.util.CharArrays.immutable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class CharArraysTest {

    @Test
    public void testEquals() {
        Assert.assertTrue(immutable("hello").equals("hello"));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(948680436, immutable("hello").hashCode());

        CharBuffer buffer = new CharBuffer();
        buffer.put('h');
        buffer.put('e');
        buffer.put('l');
        buffer.put('l');
        buffer.put('o');
        Assert.assertEquals(immutable("hello").hashCode(), buffer.hashCode());


    }

}
