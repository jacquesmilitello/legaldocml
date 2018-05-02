package io.legaldocml.akn.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.akn.visitor.Visitor;
import io.legaldocml.util.ListIterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknList<E extends AknObject> extends ExternalizableList<E> {

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final AknObject[] EMPTY_ELEMENTDATA = {};

    public AknList(E[] elem) {
        super(elem);
    }

    private AknList(E[] elem, int size) {
        super(elem, size);
    }

    /**
     * Visit all element in this list with the given {@link Visitor}.
     *
     * @param visitor the visitor for each element.
     */
    public <T extends AknVisitor> void accept(T visitor) {
        if (size() == 0) {
            return;
        }
        for (int i = 0 ; i <  size() ; i++) {
            getElems()[i].accept(visitor);
        }
    }

    public AknList<E> copy()  {
        return new AknList<E>(Arrays.copyOf(getElems(),size()), size());
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
            return AknList.this.iterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEach(Consumer<? super E> action) {
            AknList.this.forEach(action);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Spliterator<E> spliterator() {
            return Spliterators.spliterator(AknList.this, Spliterator.ORDERED);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removeAll() {
            //noinspection unchecked
            AknList.this.setElems((E[]) EMPTY_ELEMENTDATA);
            AknList.this.setSize(0);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return AknList.this.size();
        }
    }

}