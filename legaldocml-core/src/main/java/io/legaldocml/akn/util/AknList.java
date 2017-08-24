package io.legaldocml.akn.util;


import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.akn.visitor.Visitor;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AknList<E extends AknObject> implements List<E> {

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
    private static final Object[] EMPTY_ELEMENTDATA = {};

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
    public AknList() {
        this.elems = (E[]) EMPTY_ELEMENTDATA;
    }

    @SuppressWarnings("unchecked")
    public AknList(int size) {
        this.elems = (E[]) new Object[size];
    }

    public AknList(E[] elem) {
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
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param item  element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
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
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public final E get(int index) {
        return this.elems[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("set(int index, E element)");
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
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
        E[] elem = this.elems;
        for (int i = 0, size = size(), n = size; i < n; i++) {
            elem[i].write(writer);
        }
    }

//	public final Stream<E> stream() {
//		return StreamSupport.stream(spliterator(), false);
//	}

//    /**
//     * Sorts this list using the supplied Comparator to compare elements.
//     */
//    public final void order(Comparator<? super E> ordering) {
//        Arrays.sort(this.elems, ordering);
//    }
//
//    public final void forEach(Consumer<? super E> action) {
//        final int expectedModCount = _modCount;
//        final E[] elem = _elem;
//        final int size = _size;
//        for (int i = 0; _modCount == expectedModCount && i < size; i++) {
//            action.accept(elem[i]);
//        }
//        if (_modCount != expectedModCount) {
//            throw new ConcurrentModificationException();
//        }
//    }

    public void copyTo(AknList<E> list) {
        System.arraycopy(this.elems, 0, list.elems, 0, this.size);
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public final int size() {
        return this.size;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present. If the list does not
     * contain the element, it is unchanged. More formally, removes the element with the lowest index <tt>i</tt> such
     * that <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if such an element exists).
     * Returns <tt>true</tt> if this list contained the specified element (or equivalently, if this list changed as a
     * result of the call).
     *
     * @param data element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public final boolean remove(E data) {
        for (int index = 0; index < this.size; index++) {
            if (data.equals(this.elems[index])) {
                fastRemove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    public final void clear() {
        this.modCount++;

        for (int index = 0; index < this.size; index++) {
            this.elems[index] = null;
        }
        this.size = 0;
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

    /**
     * Visit all element in this list with the given {@link Visitor}.
     *
     * @param visitor the visitor for each element.
     */
    public <T extends AknVisitor> void accept(T visitor) {
        E[] elem = this.elems;
        for (int i = 0, size = size(), n = size; i < n; i++) {
            elem[i].accept(visitor);
        }
    }

    // ========================================================================
    // TODO .......
    // ========================================================================
    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("remove(index)");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException(" indexOf(Object o)");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("lastIndexOf(Object o)");
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        final E[] iterable = this.elems;
        for (int i = 0, n = this.size; i < n ; i++) {
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

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}