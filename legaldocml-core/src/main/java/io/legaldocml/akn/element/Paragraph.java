package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PARAGRAPH;

/**
 * This element is a hierarchical container called "paragraph" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="paragraph" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Paragraph extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PARAGRAPH = Buffers.address(PARAGRAPH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PARAGRAPH, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_PARAGRAPH, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PARAGRAPH;
    }

}