package io.legaldocml.unsafe;

import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.legaldocml.test.Tests.assertUtilClassIsWellDefined;

@RunWith(SonarJUnit4ClassRunner.class)
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
            Assert.fail();
        }
    }

    @Test
    public void testField() {
        UnsafeHelper.getFieldOffset(Toto.class, "hello");
        try {
            UnsafeHelper.getFieldOffset(Toto.class, "hello2");
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

    public static class Toto {
        String hello;
    }
}
