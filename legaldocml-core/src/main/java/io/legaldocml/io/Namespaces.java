package io.legaldocml.io;

import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Namespaces {

    CharArray DEFAULT_NS_PREFIX = CharArrays.constant("");

    CharArray get(CharArray prefix);

    CharArray getDefaultNamespaceUri();

    void forEach(BiConsumer<CharArray, CharArray> consumer);

    int count();

}