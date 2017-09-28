package io.legaldocml.akn.util;


import io.legaldocml.io.Externalizable;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class ExternalizableList<E extends Externalizable> implements List<E> {

    /**
     * The maximum size of array to allocate. Some VMs reserve some header words in an array. Attempts to allocate
     * larger arrays may result in OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE / 2;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Externalizable[] EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored. The capacity of the AknList is the length
     * of this array buffer. Any empty AknList with elementData == EMPTY_ELEMENTDATA will be expanded to
     * DEFAULT_CAPACITY when the first element is added.
     */
    private transient E[] elems;

    /**
     * The size of the AknList (the number of elements it contains).
     */
    private int size;

    /**
     * The number of times this list has been <i>structurally modified</i>. Structural modifications are those that
     * change the size of the list, or otherwise perturb it in such a fashion that iterations in progress may yield
     * incorrect results.
     *
     *
     * This field is used by the iterator and list iterator implementation returned by the {@code iterator} and {@code
     * listIterator} methods. If the value of this field changes unexpectedly, the iterator (or list iterator) will
     * throw a {@code ConcurrentModificationException} in response to the {@code next}, {@code remove}, {@code
     * previous}, {@code set} or {@code add} operations. This provides <i>fail-fast</i> behavior, rather than
     * non-deterministic behavior in the face of concurrent modification during iteration.
     *
     *
     * <b>Use of this field by subclasses is optional.</b> If a subclass wishes to provide fail-fast iterators (and list
     * iterators), then it merely has to increment this field in its {@code add(int, E)} and {@code remove(int)} methods
     * (and any other methods that it overrides that result in structural modifications to the list). A single call to
     * {@code add(int, E)} or {@code remove(int)} must add no more than one to this field, or the iterators (and list
     * iterators) will throw bogus {@code ConcurrentModificationExceptions}. If an implementation does not wish to
     * provide fail-fast iterators, this field may be ignored.
     */
    private transient int modCount = 0;

    @SuppressWarnings("unchecked")
    public ExternalizableList() {
        this.elems = (E[]) EMPTY_ELEMENTDATA;
    }

    public ExternalizableList(E[] elem) {
        this.elems = elem;
        this.size = 0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param item element to be appended to this list
     */
    public final boolean add(E item) {
        ensureCapacityInternal(this.size + 1);
        this.elems[this.size++] = item;
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(int index, E item) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        ensureCapacityInternal(this.size + 1);
        System.arraycopy(this.elems, index, this.elems, index + 1, this.size - index);
        this.elems[index] = item;
        this.size++;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public final E get(int index) {
        return this.elems[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("set(int index, E element)");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Write this list to the given {@link XmlWriter}.
     *
     * @param writer the writer to write all inner element.
     * @throws IOException Includes any I/O exceptions that may occur.
     */
    public void write(XmlWriter writer) throws IOException {
        E[] el = this.elems;
        int si = this.size;
        for (int i = 0; i < si; i++) {
            el[i].write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Stream<E> stream() {
        return StreamSupport.stream(Spliterators.spliterator(this.elems, 0, this.size, Spliterator.ORDERED | Spliterator.SIZED), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void forEach(Consumer<? super E> action) {
        E[] el = this.elems;
        int si = this.size;
        for (int i = 0; i < si; i++) {
            action.accept(el[i]);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean remove(Object o) {
        E[] el = this.elems;
        int si = this.size;
        for (int i = 0; i < si; i++) {
            if (o.equals(this.elems[i])) {
                fastRemove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        E e = this.elems[index];
        fastRemove(index);
        return e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void clear() {
        this.modCount++;

        for (int index = 0; index < this.size; index++) {
            this.elems[index] = null;
        }
        this.size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int indexOf(Object o) {
        Objects.requireNonNull(o);
        E[] el = this.elems;
        int si = this.size;
        for (int i = 0; i < si; i++) {
            if (el[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);
        E[] el = this.elems;
        int si = this.size;
        for (int i = si - 1; i >= 0; i--) {
            if (el[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }


    private void ensureCapacityInternal(int min) {
        int minCapacity = min;
        if (this.elems == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        this.modCount++;
        // overflow-conscious code
        if (minCapacity - this.elems.length > 0) {
            grow(minCapacity);
        }

    }

    /**
     * Increases the capacity to ensure that it can hold at least the number of elements specified by the minimum
     * capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    protected final void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = this.elems.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0) {
                // overflow
                throw new OutOfMemoryError();
            }
            newCapacity = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        // minCapacity is usually close to size, so this is a win:
        this.elems = Arrays.copyOf(this.elems, newCapacity);
    }

    /*
     * Private remove method that skips bounds checking and does not return the value removed.
     */
    private void fastRemove(int index) {
        this.modCount++;

        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.elems, index + 1, this.elems, index, numMoved);
        }
        // Let gc do its work
        this.elems[--this.size] = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append("@").append(System.identityHashCode(this));
        builder.append(" [");
        for (int i = 0; i < this.size; i++) {
            builder.append(this.elems[i]).append(",");
        }
        builder.insert(builder.length() - 1, ']');
        return builder.toString();
    }

    protected final void addUncheck(E item) {
        this.elems[this.size++] = item;
    }

    protected final E[] getElems() {
        return this.elems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        final E[] iterable = this.elems;
        for (int i = 0, n = this.size; i < n; i++) {
            if (iterable[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        final E[] iterable = this.elems;
        final int max = this.size;
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < max;
            }

            @Override
            public E next() {
                if (i >= max) {
                    throw new NoSuchElementException();
                }
                return iterable[i++];
            }
        };
    }

    // ========================================================================
    // TODO .......
    // ========================================================================
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("listIterator()");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator(int index)");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList(int fromIndex, int toIndex)");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("toArray()");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("toArray(" + a + ")");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("containsAll()");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll()");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll()");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll()");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll()");
    }

}