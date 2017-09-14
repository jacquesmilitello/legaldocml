package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.INDENT;

/**
 * This element is a hierarchical container called "indent" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="indent" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Indent extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INDENT = Buffers.address(INDENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INDENT, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_INDENT, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INDENT;
    }
}