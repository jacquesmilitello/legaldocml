package io.legaldocml.module.xml;

import io.legaldocml.util.Buffers;
import io.legaldocml.util.CharArray;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.module.xml.attribute.XmlSpace;
import io.legaldocml.module.xml.type.Space;

import java.io.IOException;

import static io.legaldocml.unsafe.UnsafeString.getChars;

final class XmlSpaceImpl implements XmlSpace {

    public static final String ATTRIBUTE = "xml:space";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ATTRIBUTE);

    private Space value;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        if (this.value == null) {

        } else {
            writer.writeAttribute(ADDRESS, 9, getChars(this.value.toString()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader, CharArray value) {
        //        // BADDDDDDDD.
        this.value = Space.valueOf(value.toString().toUpperCase());

        if (Space.PRESERVE == this.value) {
            reader.preserveSpace();
        }



    }

    @Override
    public Space getSpace() {
        return this.value;
    }

    @Override
    public void setSpace(Space space) {
        this.value = space;
    }
}
