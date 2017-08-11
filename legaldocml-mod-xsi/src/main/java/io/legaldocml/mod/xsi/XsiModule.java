package io.legaldocml.mod.xsi;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.Module;
import io.legaldocml.mod.xsi.attribute.SchemaLocation;

import java.io.IOException;
import java.util.function.Supplier;

public final class XsiModule implements Module {

    public static final String NS_VALUE = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String NS_PREFIX = "xmlns:xsi";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    private static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    private static final ImmutableMap<String, Supplier<Attribute>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, Supplier<Attribute>>builder()
                .put(SchemaLocation.ATTRIBUTE, SchemaLocationImpl::new)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS, 9, NS_VALUE_ADDRESS, 41);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<Attribute> attributes(String name) {
        return ATTRIBUTES.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }
}
