package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element source is a metadata element specifying the IRI of the source of the modification.
 * <p/>
 * <pre>
 *   <xsd:element name="source" type="argumentType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Source extends ArgumentType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "source";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}