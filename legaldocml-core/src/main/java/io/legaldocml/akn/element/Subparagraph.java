package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUB_PARAGRAPH;

/**
 * This element is a hierarchical container called "subparagraph" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="subparagraph" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Subparagraph extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB_PARAGRAPH = Buffers.address(SUB_PARAGRAPH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB_PARAGRAPH, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB_PARAGRAPH, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB_PARAGRAPH;
    }
}