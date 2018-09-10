package io.legaldocml.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.util.AknList;

import java.util.Iterator;

import static java.util.Collections.emptyIterator;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Iterables {

    private Iterables() {
    }

    private static final ListIterable<Object> EMPTY = new ListIterable<Object>() {
        /**
         * {@inheritDoc}
         */
        @Override
        public Iterator<Object> iterator() {
            return emptyIterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object get(int index) {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removeAll() {
            // nothing to do.
        }

    };

    public static <T> ListIterable<T> empty() {
        @SuppressWarnings("unchecked")
        ListIterable<T> t = (ListIterable<T>) EMPTY;
        return t;
    }

    public static <T extends AknObject> ListIterable<T> iterable(AknList<T> list) {
        if (list == null) {
            return empty();
        } else {
            return list.iterable();
        }
    }

}