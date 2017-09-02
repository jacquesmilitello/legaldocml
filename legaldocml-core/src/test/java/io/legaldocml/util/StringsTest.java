package io.legaldocml.util;

import io.legaldocml.test.Tests;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class StringsTest {

    @Test
    public void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Strings.class);
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(Strings.isBlank(null));
        Assert.assertTrue(Strings.isBlank(""));
        Assert.assertTrue(Strings.isBlank(" "));
        Assert.assertFalse(Strings.isBlank("jacques"));
        Assert.assertFalse(Strings.isBlank("  jacques  "));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(Strings.isEmpty(null));
        Assert.assertTrue(Strings.isEmpty(""));
        Assert.assertFalse(Strings.isEmpty(" "));
        Assert.assertFalse(Strings.isEmpty("jacques"));
        Assert.assertFalse(Strings.isEmpty("  jacques  "));
    }

}
