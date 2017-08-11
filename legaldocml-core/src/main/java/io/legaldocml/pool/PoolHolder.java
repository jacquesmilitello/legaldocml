package io.legaldocml.pool;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PoolHolder<T> extends Supplier<T> {

    /**
     * State FREE.
     */
    int FREE = 0;
    /**
     * State USED.
     */
    int USED = 1;
    /**
     * State Single.
     */
    int SINGLE = 2;

    int getState();

    void setState(int state);
}
