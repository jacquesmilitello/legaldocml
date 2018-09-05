package io.legaldocml.io;

import io.legaldocml.io.impl.XmlChannelReader;
import io.legaldocml.util.CharArray;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AttributeConsumer<T extends Externalizable> {

    void set(XmlChannelReader reader, T object, CharArray name, CharArray value, int prefixNS);

}