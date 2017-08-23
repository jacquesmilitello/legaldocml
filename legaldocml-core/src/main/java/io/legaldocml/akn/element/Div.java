package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element div is an HTML element, but is NOT used in Akoma Ntoso as in HTML. Instead of being used as a generic
 * block, Akoma Ntoso uses div as a generic container (as in common practice).
 *
 * <pre>
 *   <xsd:element name="div" type="blocksreq">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Div extends Blocksreq implements HTMLcontainers {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "div";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}