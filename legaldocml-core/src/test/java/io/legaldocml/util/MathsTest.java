package io.legaldocml.util;

import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals(1000*10, Maths.mul10(1000));
    }

    @Test
    public void testDiv10() {
        Assert.assertEquals(1000/10, Maths.div10(1000));
        Assert.assertEquals(990/10, Maths.div10(990));
        Assert.assertEquals(12/10, Maths.div10(12));
        Assert.assertEquals(1/10, Maths.div10(1));
        Assert.assertEquals(123456789/10, Maths.div10(123456789));
    }

    @Test
    public void testUnsignedDiv10() {
        Assert.assertEquals(1000/10, Maths.unsignedDiv10(1000));
        Assert.assertEquals(990/10, Maths.unsignedDiv10(990));
        Assert.assertEquals(12/10, Maths.unsignedDiv10(12));
        Assert.assertEquals(1/10, Maths.unsignedDiv10(1));
        Assert.assertEquals(123456789/10, Maths.unsignedDiv10(123456789));
    }



    @Test
    public void testDiv100() {
        Assert.assertEquals(1000/100, Maths.div100(1000));
        Assert.assertEquals(990/100, Maths.div100(909));
        Assert.assertEquals(12/100, Maths.div100(12));
    }

    @Test
    public void testUnsignedDiv100() {
        Assert.assertEquals(1000/100, Maths.div100(1000));
        Assert.assertEquals(990/100, Maths.div100(909));
        Assert.assertEquals(12/100, Maths.div100(12));
    }


    @Test
    public void testUnsignedDiv1000() {
        Assert.assertEquals(1000/1000, Maths.unsignedDiv1000(1000));
        Assert.assertEquals(90000/1000, Maths.unsignedDiv1000(90000));
        Assert.assertEquals(12/1000, Maths.unsignedDiv1000(12));
        Assert.assertEquals(897654/1000, Maths.unsignedDiv1000(897654));
    }

}
