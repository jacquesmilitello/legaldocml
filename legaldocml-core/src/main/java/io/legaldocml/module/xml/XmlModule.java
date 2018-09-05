package io.legaldocml.module.xml;


import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.io.CoreAttribute;
import io.legaldocml.module.xml.attribute.XmlId;
import io.legaldocml.util.CharArray;
import io.legaldocml.util.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;
import io.legaldocml.module.Module;
import io.legaldocml.module.xml.attribute.XmlLang;
import io.legaldocml.module.xml.attribute.XmlSpace;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class XmlModule implements Module {

    public static final String NS_VALUE = "http://www.w3.org/XML/1998/namespace";

    public static final String NS_PREFIX = "xmlns:xml";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);

    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    public static final CharArray NAMESPACE_XML = CharArrays.immutable(NS_VALUE);

    private static final ImmutableMap<String, Supplier<CoreAttribute>> ATTRIBUTES;

    static {
        ATTRIBUTES = ImmutableMap.<String, Supplier<CoreAttribute>>builder()
                .put(XmlLang.ATTRIBUTE, XmlLangImpl::new)
                .put(XmlSpace.ATTRIBUTE, XmlSpaceImpl::new)
                .put(XmlId.ATTRIBUTE, XmlIdImpl::new)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharArray namespace() {
        return NAMESPACE_XML;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS, 9, NS_VALUE_ADDRESS, 36);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<CoreAttribute> attribute(String name) {
        return ATTRIBUTES.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AknObject> Supplier<T> element(String localName) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE_XML + "]";
    }

}
