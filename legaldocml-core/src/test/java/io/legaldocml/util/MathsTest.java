package io.legaldocml.util;

import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class MathsTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Maths.class);
    }

    @Test
    public void testMul10() {
        assertEquals(1000*10, Maths.mul10(1000));
    }

    @Test
    public void testMul100() {
        assertEquals(1000*100, Maths.mul100(1000));
    }

    @Test
    public void testDiv10() {
        assertEquals(1000/10, Maths.div10(1000));
        assertEquals(990/10, Maths.div10(990));
        assertEquals(12/10, Maths.div10(12));
        assertEquals(1/10, Maths.div10(1));
        assertEquals(123456789/10, Maths.div10(123456789));
    }

    @Test
    public void testUnsignedDiv10() {
        assertEquals(1000/10, Maths.unsignedDiv10(1000));
        assertEquals(990/10, Maths.unsignedDiv10(990));
        assertEquals(12/10, Maths.unsignedDiv10(12));
        assertEquals(1/10, Maths.unsignedDiv10(1));
        assertEquals(123456789/10, Maths.unsignedDiv10(123456789));
    }



    @Test
    public void testDiv100() {
        assertEquals(1000/100, Maths.div100(1000));
        assertEquals(990/100, Maths.div100(909));
        assertEquals(12/100, Maths.div100(12));
    }

    @Test
    public void testUnsignedDiv100() {
        assertEquals(1000/100, Maths.unsignedDiv100(1000));
        assertEquals(990/100, Maths.unsignedDiv100(909));
        assertEquals(12/100, Maths.unsignedDiv100(12));
    }


    @Test
    public void testUnsignedDiv1000() {
        assertEquals(1000/1000, Maths.unsignedDiv1000(1000));
        assertEquals(90000/1000, Maths.unsignedDiv1000(90000));
        assertEquals(12/1000, Maths.unsignedDiv1000(12));
        assertEquals(897654/1000, Maths.unsignedDiv1000(897654));
    }

}
