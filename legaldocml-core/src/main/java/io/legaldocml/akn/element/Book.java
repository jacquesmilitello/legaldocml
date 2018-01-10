package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BOOK;

/**
 * This element is a hierarchical container called "book" either explicitly or due to the local tradition.
 *
 * <pre>
 * 	 <xsd:element name="book" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Book extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BOOK = Buffers.address(BOOK);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BOOK, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_BOOK, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BOOK;
    }
}