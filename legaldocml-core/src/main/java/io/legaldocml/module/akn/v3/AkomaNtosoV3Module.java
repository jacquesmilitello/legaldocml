package io.legaldocml.module.akn.v3;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.AknModule;

import java.io.IOException;
import java.util.function.Supplier;

public final class AkomaNtosoV3Module implements AknModule {

    public static final String NS_VALUE = "http://docs.oasis-open.org/legaldocml/ns/akn/3.0";
    public static final String NS_PREFIX = "xmlns";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    private static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }

    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS,5, NS_VALUE_ADDRESS,48);
    }

    @Override
    public Supplier<Attribute> attributes(String name) {
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }


    @Override
    public int getVersion() {
        return 3;
    }
}
