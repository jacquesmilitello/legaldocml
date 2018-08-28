package io.legaldocml.util;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
class StringsTest {

    @Test
    void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Strings.class);
    }

    @Test
    void testIsBlank() {
        assertTrue(Strings.isBlank(null));
        assertTrue(Strings.isBlank(""));
        assertTrue(Strings.isBlank(" "));
        assertFalse(Strings.isBlank("jacques"));
        assertFalse(Strings.isBlank("  jacques  "));
    }

    @Test
    void testIsEmpty() {
        assertTrue(Strings.isEmpty(null));
        assertTrue(Strings.isEmpty(""));
        assertFalse(Strings.isEmpty(" "));
        assertFalse(Strings.isEmpty("jacques"));
        assertFalse(Strings.isEmpty("  jacques  "));
    }

    @Test
    void testSplit() {
        List<String> list = Strings.split(' ',"a small test");
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("small", list.get(1));
        assertEquals("test", list.get(2));
        assertEquals(1, Strings.split(' ',"a_small_test").size());
    }
}
