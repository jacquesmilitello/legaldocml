package io.legaldocml.akn.type;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.unsafe.UnsafeString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggerInstancePostProcessor.class)
class ListReferenceRefTest {

    @Test
    void testSingle() {
        ListReferenceRef ref = ListReferenceRefs.parse("#hello".toCharArray());
        assertEquals(1, ref.size());
        assertTrue(ref.get(0).isRef());
        assertEquals("#hello", UnsafeString.valueOf(ref.get(0).getChars()));
    }

    @Test
    void testMultiple() {
        ListReferenceRef ref = ListReferenceRefs.parse("#hello #test value".toCharArray());
        assertEquals(3, ref.size());
        assertTrue(ref.get(0).isRef());
        assertEquals("#hello", UnsafeString.valueOf(ref.get(0).getChars()));
        assertTrue(ref.get(1).isRef());
        assertEquals("#test", UnsafeString.valueOf(ref.get(1).getChars()));
        assertFalse(ref.get(2).isRef());
        assertEquals("value", UnsafeString.valueOf(ref.get(2).getChars()));
    }

    @Test
    void testAdd() {
        ListReferenceRef ref = ListReferenceRefs.make(ReferenceRef.valueOf("hello"));
        ref.add(ReferenceRef.valueOf("toto"));
    }
}
