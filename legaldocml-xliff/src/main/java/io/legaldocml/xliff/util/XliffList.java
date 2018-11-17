package io.legaldocml.xliff.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.util.ListIterable;
import io.legaldocml.xliff.element.XliffObject;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XliffList<E extends XliffObject> extends ExternalizableList<E> {

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final AknObject[] EMPTY_ELEMENTDATA = {};

    public XliffList(E[] elem) {
        super(elem);
    }

    private XliffList(E[] elem, int size) {
        super(elem, size);
    }

    public final ListIterable<E> iterable() {
        return new IterableImpl();
    }

    private final class IterableImpl implements ListIterable<E> {

        /**
         * {@inheritDoc}
         */
        @Override
        public Iterator<E> iterator() {
            return XliffList.this.iterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEach(Consumer<? super E> action) {
            XliffList.this.forEach(action);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Spliterator<E> spliterator() {
            return Spliterators.spliterator(XliffList.this, Spliterator.ORDERED);
        }

        @Override
        public E get(int index) {
            return XliffList.this.get(index);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removeAll() {
            //noinspection unchecked
            XliffList.this.setElems((E[]) EMPTY_ELEMENTDATA);
            XliffList.this.setSize(0);
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return XliffList.this.size();
        }
    }

}