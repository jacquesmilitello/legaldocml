package io.legaldocml.akn.util;

import io.legaldocml.akn.element.AbstractId;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoggerInstancePostProcessor.class)
class ExternalizableListTest {


    @Test
    void testRemove() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);

        assertEquals(3, list.size());
        list.remove(id1);
        assertEquals(2, list.size());

        assertSame(id2, list.get(0));
        assertSame(id3, list.get(1));
    }

    @Test
    void testRemoveIndex() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);

        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());

        assertSame(id2, list.get(0));
        assertSame(id3, list.get(1));
    }

    @Test
    void testAddWithIndex() {

        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(0, id2);
        list.add(1, id3);

        assertSame(id2, list.get(0));
        assertSame(id3, list.get(1));
        assertSame(id1, list.get(2));
    }

    @Test
    void testIteratorRemove() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();
        list.add(id1);
        list.add(id2);
        list.add(id3);

        Iterator<SimpleId> iterator = list.iterator();

        assertThrows(IllegalStateException.class, iterator::remove);

        iterator.next();
        iterator.remove();

        assertEquals(2, list.size());
        assertSame(id2, list.get(0));
        assertSame(id3, list.get(1));

    }

    @Test
    void testAddWithIndexException() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, id1));
    }

    @Test
    void testForEach() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);

        AtomicInteger integer = new AtomicInteger();
        list.forEach(t -> integer.incrementAndGet());

        assertEquals(3, integer.get());
    }

    @SuppressWarnings("all")
    @Test
    void testForStream() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();
        list.add(id1);
        list.add(id2);
        list.add(id3);

        assertEquals(3, list.stream().count());
    }

    @Test
    void testToString() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();
        list.add(id1);
        list.add(id2);
        list.add(id3);

        Assertions.assertNotNull(list.toString());
    }


    @Test
    void testClear() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>(new SimpleId[4]);
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);

        list.clear();
        assertEquals(0, list.size());
        SimpleId[] elems = list.getElems();
        for (SimpleId elem : elems) {
            Assertions.assertNull(elem);
        }
    }

    @SuppressWarnings({"unlikely-arg-type", "SuspiciousMethodCalls"})
	@Test
    void testIndexOf() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);

        assertEquals(0, list.indexOf(id1));
        assertEquals(1, list.indexOf(id2));
        assertEquals(2, list.indexOf(id3));
        assertEquals(-1, list.indexOf("hello"));

        Assertions.assertThrows(NullPointerException.class, () -> list.indexOf(null));
    }

    @SuppressWarnings({"unlikely-arg-type", "SuspiciousMethodCalls"})
	@Test
    void testLastIndexOf() {
        ExternalizableList<SimpleId> list = new ExternalizableList<>();
        SimpleId id1 = new SimpleId();
        SimpleId id2 = new SimpleId();
        SimpleId id3 = new SimpleId();

        list.add(id1);
        list.add(id2);
        list.add(id3);
        list.add(id1);
        list.add(id2);
        list.add(id3);

        assertEquals(3, list.lastIndexOf(id1));
        assertEquals(4, list.lastIndexOf(id2));
        assertEquals(5, list.lastIndexOf(id3));
        assertEquals(-1, list.lastIndexOf("hello"));

        Assertions.assertThrows(NullPointerException.class, () -> list.lastIndexOf(null));
    }

    public static class SimpleId extends AbstractId {
        @Override
        public String name() {
            return null;
        }

        @Override
        public void write(XmlWriter writer) {
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
    }

}
