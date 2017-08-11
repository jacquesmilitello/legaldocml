package io.legaldocml.pool;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface PoolableObject<T> {

    T newInstance();

    void passivate(T t);
}