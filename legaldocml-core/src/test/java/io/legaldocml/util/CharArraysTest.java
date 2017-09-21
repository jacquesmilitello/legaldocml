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

        CharArray array = immutable("hello");

        Assert.assertTrue(array.equals("hello"));
        Assert.assertEquals(array,immutable("hello"));
        Assert.assertEquals(array,array);

        Assert.assertFalse(array.equals(null));
        Assert.assertFalse(array.equals(Integer.valueOf(25)));

        Assert.assertNotEquals(array,immutable("hellO"));
        Assert.assertNotEquals(array,immutable("helloo"));
        Assert.assertNotEquals(array,"hellO");
        Assert.assertNotEquals(array,"helloo");
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
