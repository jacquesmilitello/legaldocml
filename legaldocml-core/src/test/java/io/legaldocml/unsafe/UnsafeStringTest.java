package io.legaldocml.unsafe;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.test.Tests.assertUtilClassIsWellDefined;

/**
 * Test the Dark Side of Java.
 */
@ExtendWith(LoggerInstancePostProcessor.class)
public class UnsafeStringTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        assertUtilClassIsWellDefined(UnsafeString.class);
    }

   @Test
    public void buildStringTest() {
        char[] helloWorld = "helloWorld".toCharArray();

        String val = UnsafeString.valueOf(helloWorld);
        Assertions.assertEquals("helloWorld", val);
    }

    @Test
    public void darkSideStringTest() {
        if (Jvm.isJava8()) {
            char[] helloWorld = "helloWorld".toCharArray();
            String val = UnsafeString.valueOf(helloWorld);
            Assertions.assertEquals("helloWorld", val);
            helloWorld[0] = 'H';
            Assertions.assertEquals("HelloWorld", val);
        }
    }

   @Test
    public void getCharsTest() {
       if (Jvm.isJava8()) {
           String helloWorld = "HelloWorld";
           char[] value = UnsafeString.getChars(helloWorld);
           Assertions.assertArrayEquals("HelloWorld".toCharArray(), value);
           value[0] = 'P';
           Assertions.assertArrayEquals("PelloWorld".toCharArray(), value);
           Assertions.assertEquals("PelloWorld", helloWorld);
           value[0] = 'H';
       }
    }

     @Test
    public void testNull() {
        Assertions.assertEquals(Strings.EMPTY,UnsafeString.valueOf(null));
        Assertions.assertEquals(Strings.EMPTY,UnsafeString.valueOf(new char[0]));

        Assertions.assertNotNull(UnsafeString.getChars(null));
    }


}
