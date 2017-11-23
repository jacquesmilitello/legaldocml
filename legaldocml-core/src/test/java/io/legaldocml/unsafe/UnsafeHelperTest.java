package io.legaldocml.unsafe;

import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.test.Tests.assertUtilClassIsWellDefined;

@ExtendWith(LoggerInstancePostProcessor.class)
public class UnsafeHelperTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        assertUtilClassIsWellDefined(UnsafeHelper.class);
    }

    @Test
    @SuppressWarnings("restriction")
    public void testSimpleObject() {
        try {
            UnsafeHelper.getUnsafe().allocateInstance(Object.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
            Assertions.fail("");
        }
    }

    @Test
    public void testField() {
        UnsafeHelper.getFieldOffset(Toto.class, "hello");
        try {
            UnsafeHelper.getFieldOffset(Toto.class, "hello2");
            Assertions.fail("");
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

    public static class Toto {
        String hello;
    }
}
