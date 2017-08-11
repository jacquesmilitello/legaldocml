package io.legaldocml.io;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AttributeConsumer<T> {

    void set(T object, CharArray name, CharArray value, int prefixNS);

}