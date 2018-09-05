package io.legaldocml.io.impl;


import io.legaldocml.io.AttributeConsumer;
import io.legaldocml.io.QName;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharBuffer;
import io.legaldocml.io.Externalizable;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XmlAttributes {

    /**
     * Default buffer size for names and values.
     */
    public static final int DEFAULT_BUFFER_SIZE = 32;

    /**
     * Char array for attributes name.
     */
    private final CharArray[] names = new CharArray[DEFAULT_BUFFER_SIZE];

    /**
     * Char array for attributes value.
     */
    private final CharArray[] values = new CharArray[DEFAULT_BUFFER_SIZE];

    /**
     * int array for prefix positions.
     */
    private final int[] prefixes = new int[DEFAULT_BUFFER_SIZE];

    /**
     * Number of attributes.
     */
    private int pos;

    public void addAttribute(CharBuffer qname, int prefix, CharBuffer value) {
        this.prefixes[pos] = prefix;
        this.names[pos] = qname;
        this.values[pos++] = value;
    }

    public void reset() {
        pos = 0;
    }

    public <T extends Externalizable> void forEach(XmlChannelReader reader, T object, AttributeConsumer<T> consumer) {
        for (int i = 0; i < pos; i++) {
            consumer.set(reader, object, this.names[i], this.values[i], this.prefixes[i]);
        }
    }

}
