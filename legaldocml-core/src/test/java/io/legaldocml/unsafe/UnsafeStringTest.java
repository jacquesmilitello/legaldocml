package io.legaldocml.unsafe;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import io.legaldocml.util.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.legaldocml.test.Tests.assertUtilClassIsWellDefined;

/**
 * Test the Dark Side of Java.
 */
@RunWith(SonarJUnit4ClassRunner.class)
public class UnsafeStringTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        assertUtilClassIsWellDefined(UnsafeString.class);
    }

    @Test
    public void buildStringTest() {
        char[] helloWorld = "helloWorld".toCharArray();

        String val = UnsafeString.buildUnsafe(helloWorld);
        Assert.assertEquals("helloWorld", val);
    }

    @Test
    public void darkSideStringTest() {
        char[] helloWorld = "helloWorld".toCharArray();
        String val = UnsafeString.buildUnsafe(helloWorld);
        Assert.assertEquals("helloWorld", val);
        helloWorld[0] = 'H';
        Assert.assertEquals("HelloWorld", val);
    }

    @Test
    public void getCharsTest() {
        String helloWorld = "HelloWorld";
        char[] value = UnsafeString.getChars(helloWorld);
        Assert.assertArrayEquals("HelloWorld".toCharArray(), value);
        value[0] = 'P';
        Assert.assertArrayEquals("PelloWorld".toCharArray(), value);
        Assert.assertEquals("PelloWorld", helloWorld);
        value[0] = 'H';
    }

    @Test
    public void testNull() {
        Assert.assertEquals(Strings.EMPTY,UnsafeString.valueOf(null));
        Assert.assertEquals(Strings.EMPTY,UnsafeString.valueOf(new char[0]));
        Assert.assertEquals(Strings.EMPTY,UnsafeString.buildUnsafe(null));
        Assert.assertEquals(Strings.EMPTY,UnsafeString.buildUnsafe(new char[0]));

        Assert.assertNotNull(UnsafeString.getChars(null));
        Assert.assertEquals(0,UnsafeString.buildUnsafe(new char[0]).length());
    }


}
