package io.legaldocml.pool;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Pool<T> {

    T checkOut();

    void checkIn(T t);

}
