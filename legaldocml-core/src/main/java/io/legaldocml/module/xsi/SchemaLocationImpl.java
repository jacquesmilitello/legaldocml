package io.legaldocml.module.xsi;

import io.legaldocml.util.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.xsi.attribute.SchemaLocation;
import io.legaldocml.unsafe.UnsafeString;

import java.io.IOException;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class SchemaLocationImpl implements SchemaLocation {

    public static final String ATTRIBUTE = "xsi:schemaLocation";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ATTRIBUTE);

    private String schemaLocation;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSchemaLocation() {
        return this.schemaLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeAttribute(ADDRESS, 18, UnsafeString.getChars(this.schemaLocation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {
        this.schemaLocation = value.toString();
    }
}
