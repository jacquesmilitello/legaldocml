package io.legaldocml.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IterablesTest {

    @Test
    void testUtilClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(Iterables.class);
    }

    @Test
    <T extends AknObject> void testEmpty() {
        AknList<T> e = null;
        ListIterable<T>  empty = Iterables.iterable(e);
        assertFalse(empty.iterator().hasNext());
        assertEquals(0, empty.size());
    }

    @Test
    void testIterable() {
        ListIterable<AknObject>  empty = Iterables.iterable(new AknList<>(new AknObject[0]));
        assertFalse(empty.iterator().hasNext());
        assertEquals(0, empty.size());
    }


}
