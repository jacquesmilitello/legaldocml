package io.legaldocml.pool;


import io.legaldocml.unsafe.UnsafeHelper;

import java.io.Closeable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class UnsafePool<T> implements Pool<PoolHolder<T>>, Closeable{

    private final long adr;

    @SuppressWarnings("restriction")
	private static final sun.misc.Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    private final UnsafeHolder<T>[] holders;

    private final int size;

    private final PoolableObject<T> poolableObject;

    private int index;

    private boolean isClosed;

    @SuppressWarnings({ "restriction", "unchecked" })
    UnsafePool(int size, PoolableObject<T> poolableObject) {
        this.poolableObject = poolableObject;
        this.adr = UNSAFE.allocateMemory((long)size * 8);
        this.holders = new UnsafeHolder[size];
        for (int i = 0; i < size; i++) {
            holders[i] = new UnsafeHolder<T>(poolableObject.newInstance());
        }
        this.size = size;
        this.isClosed = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
    public UnsafeHolder<T> checkOut() {

        int size = this.size;
        int index = this.index;
        UnsafeHolder<T>[] holders = this.holders;
        for (int i = index; i < size; i++) {
            if (UNSAFE.compareAndSwapInt(holders[i], UnsafeHolder.FIELD_OFFSET, UnsafeHolder.FREE, UnsafeHolder.USED)) {
                // it's free => return;
                this.index = i;
                return holders[i];
            }
        }
        for (int i = 0; i < index; i++) {
            if (UNSAFE.compareAndSwapInt(holders[i], UnsafeHolder.FIELD_OFFSET, UnsafeHolder.FREE, UnsafeHolder.USED)) {
                this.index = i;
                // it's free => return;
                return holders[i];
            }
        }
        // all are used => retry
        return checkOut(0, size, holders);
    }

    @SuppressWarnings("restriction")
    private UnsafeHolder<T> checkOut(int count, int size, UnsafeHolder<T>[] holders) {

        if (count == 100) {
            return new UnsafeHolder<>(poolableObject.newInstance(), UnsafeHolder.SINGLE);
        }

        Thread.yield();

        for (int i = 0; i < size; i++) {
            if (UNSAFE.compareAndSwapInt(holders[i], UnsafeHolder.FIELD_OFFSET, UnsafeHolder.FREE, UnsafeHolder.USED)) {
                return holders[i];
            }
        }

        return checkOut(count + 1, size, holders);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void checkIn(PoolHolder<T> holder) {


        // single instance => not a member of the pool ==> skip
        if (UnsafeHolder.SINGLE == holder.getState()) {
            return;
        }

        holder.setState(UnsafeHolder.FREE);

        this.poolableObject.passivate(holder.get());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("restriction")
    public void close() {
        if (!this.isClosed) {
            UNSAFE.freeMemory(this.adr);
            this.isClosed = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

}