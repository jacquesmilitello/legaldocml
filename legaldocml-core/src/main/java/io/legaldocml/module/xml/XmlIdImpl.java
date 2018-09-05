package io.legaldocml.module.xml;

import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.xml.attribute.XmlId;
import io.legaldocml.util.Buffers;
import io.legaldocml.util.CharArray;

import java.io.IOException;

import static io.legaldocml.unsafe.UnsafeString.getChars;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class XmlIdImpl implements XmlId {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ATTRIBUTE_WITH_NAMESPACE);

    private String id;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getXmlId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXmlId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public void write(XmlWriter writer) throws IOException {
        if (this.id != null) {
            writer.writeAttribute(ADDRESS, 6, getChars(this.id));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {
        this.id = value.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ATTRIBUTE_WITH_NAMESPACE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value() {
        return String.valueOf(this.id);
    }

}
