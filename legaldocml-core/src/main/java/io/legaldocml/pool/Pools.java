package io.legaldocml.pool;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Pools {

    private Pools() {
    }

    /**
     * create a Pool of objects with the given size.
     */
    public static <T> Pool<PoolHolder<T>> createPool(int size, PoolableObject<T> poolableObject) {
        return new UnsafePool<>(size, poolableObject);
    }

}
