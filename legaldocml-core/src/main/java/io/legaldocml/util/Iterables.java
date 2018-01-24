package io.legaldocml.util;

import io.legaldocml.akn.util.ExternalizableList;
import io.legaldocml.io.Externalizable;

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
    };

    public static <T> ListIterable<T> empty() {
        @SuppressWarnings("unchecked")
        ListIterable<T> t = (ListIterable<T>) EMPTY;
        return t;
    }

    public static <T extends Externalizable> ListIterable<T> iterable(ExternalizableList<T> list) {
        if (list == null) {
            return empty();
        } else {
            return list.iterable();
        }
    }

}