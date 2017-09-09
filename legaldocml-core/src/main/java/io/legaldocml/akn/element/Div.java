package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLcontainers;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DIV;

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
     * Memory address.
     */
    private static final long ADDRESS_DIV = Buffers.address(DIV);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DIV, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_DIV, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DIV;
    }

}