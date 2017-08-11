package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element wrapUp is a concluding element in a hierarchy that contains paragraphs wrapping up the preceding lower
 * hierarchical elements.
 * <p/>
 * <pre>
 *   <xsd:element name="wrap" type="blocksreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WrapUp extends Blocksreq {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "wrapUp";

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