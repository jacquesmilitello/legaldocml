package io.legaldocml.io;

import io.legaldocml.util.CharArray;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AttributeConsumer {

    void set(Externalizable object, CharArray name, CharArray value, int prefixNS);

}