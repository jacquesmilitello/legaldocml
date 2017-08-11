package io.legaldocml.mod.xsi;


import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.mod.xsi.attribute.SchemaLocation;

import java.io.IOException;

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
