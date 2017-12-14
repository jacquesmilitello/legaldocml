package io.legaldocml.io;

import io.legaldocml.util.CharArray;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AttributeGetterSetter<T> extends BiConsumer<T, CharArray>, Function<T, Object> {

    String name();

}
