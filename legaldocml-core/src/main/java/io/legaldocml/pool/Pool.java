package io.legaldocml.pool;

import java.io.Closeable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Pool<T> extends Closeable {

    T checkOut();

    void checkIn(T t);

    void close();

}
