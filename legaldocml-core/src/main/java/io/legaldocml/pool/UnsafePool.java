package io.legaldocml.pool;


import io.legaldocml.unsafe.UnsafeHelper;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class UnsafePool<T> implements Pool<PoolHolder<T>> {

    private final long adr;

    private static final sun.misc.Unsafe UNSAFE = UnsafeHelper.getUnsafe();

    private final UnsafeHolder[] holders;

    private final int size;

    private final PoolableObject<T> poolableObject;

    private int index;

    public UnsafePool(int size, PoolableObject<T> poolableObject) {
        this.poolableObject = poolableObject;
        this.adr = UNSAFE.allocateMemory(size * 8);
        this.holders = new UnsafeHolder[size];
        for (int i = 0; i < size; i++) {
            holders[i] = new UnsafeHolder(poolableObject.newInstance());
        }
        this.size = size;
    }

    @Override
    public UnsafeHolder<T> checkOut() {

        int size = this.size;
        int index = this.index;
        UnsafeHolder[] holders = this.holders;
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

    private UnsafeHolder<T> checkOut(int count, int size, UnsafeHolder[] holders) {

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


    @Override
    public void checkIn(PoolHolder<T> holder) {


        // single instance => not a member of the pool ==> skip
        if (UnsafeHolder.SINGLE == holder.getState()) {
            return;
        }

        holder.setState(UnsafeHolder.FREE);

        this.poolableObject.passivate(holder.get());

    }

    @Override
    protected void finalize() throws Throwable {
        UNSAFE.freeMemory(this.adr);
        super.finalize();
    }
}