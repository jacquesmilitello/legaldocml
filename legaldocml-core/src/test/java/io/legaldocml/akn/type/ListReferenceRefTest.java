package io.legaldocml.akn.type;

import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.unsafe.UnsafeString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
class ListReferenceRefTest {

    @Test
    void testSingle() {
        ListReferenceRef ref = new ListReferenceRef("#hello".toCharArray());
        assertEquals(1, ref.size());
        assertEquals(true, ref.get(0).isRef());
        assertEquals("#hello", UnsafeString.valueOf(ref.get(0).getChars()));
    }

    @Test
    void testMultiple() {
        ListReferenceRef ref = new ListReferenceRef("#hello #test value".toCharArray());
        assertEquals(3, ref.size());
        assertEquals(true, ref.get(0).isRef());
        assertEquals("#hello", UnsafeString.valueOf(ref.get(0).getChars()));
        assertEquals(true, ref.get(1).isRef());
        assertEquals("#test", UnsafeString.valueOf(ref.get(1).getChars()));
        assertEquals(false, ref.get(2).isRef());
        assertEquals("value", UnsafeString.valueOf(ref.get(2).getChars()));
    }

    @Test
    void testAdd() {
        ListReferenceRef ref = new ListReferenceRef();
        ref.add(ReferenceRef.valueOf("hello"));

        assertEquals("#hello", new String(ref.getChars()));

        ref.add(ReferenceRef.valueOf("toto"));
        assertEquals("#hello #toto", new String(ref.getChars()));
    }
}
