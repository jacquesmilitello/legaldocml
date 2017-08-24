package io.legaldocml.iso;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class Iso639Test {

    @Test
    public void test2Chars() {
        assertEquals(Iso639.FRENCH, Iso639.from("fr"));
        assertEquals(Iso639.FRENCH, Iso639.from("fra"));
        assertEquals(Iso639.FRENCH, Iso639.from("fre"));
    }
}
