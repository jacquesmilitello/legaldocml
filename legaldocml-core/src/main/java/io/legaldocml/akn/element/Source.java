package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SOURCE;

/**
 * The element source is a metadata element specifying the IRI of the source of the modification.
 *
 * <pre>
 *   <xsd:element name="source" type="argumentType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Source extends ArgumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SOURCE = Buffers.address(SOURCE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SOURCE, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_SOURCE, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SOURCE;
    }

}