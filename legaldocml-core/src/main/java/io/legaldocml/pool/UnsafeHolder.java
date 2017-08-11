package io.legaldocml.pool;

import io.legaldocml.unsafe.UnsafeHelper;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class UnsafeHolder<T> implements PoolHolder<T> {

    /**
     * Object to hold.
     */
    private final T value;

    static final long FIELD_OFFSET = UnsafeHelper.getFieldOffset(UnsafeHolder.class, "state");

    /**
     * internal state.
     */
    int state;

    UnsafeHolder(T value) {
        this.value = value;
        state = FREE;
    }

    public UnsafeHolder(T value, int state) {
        this.value = value;
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(int state) {
        this.state = state;
    }
}
