package io.legaldocml.mod.xml;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.module.Module;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.mod.xml.attribute.XmlLang;
import io.legaldocml.mod.xml.attribute.XmlSpace;

import java.io.IOException;
import java.util.function.Supplier;

public final class XmlModule implements Module {

    public static final String NS_VALUE = "http://www.w3.org/XML/1998/namespace";
    public static final String NS_PREFIX = "xmlns:xml";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    private static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    private static final ImmutableMap<String, Supplier<Attribute>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, Supplier<Attribute>>builder()
                .put(XmlLang.ATTRIBUTE, XmlLangImpl::new)
                .put(XmlSpace.ATTRIBUTE, XmlSpaceImpl::new)
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
        writer.writeNamespace(NS_PREFIX_ADDRESS, 9, NS_VALUE_ADDRESS, 36);
    }

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
