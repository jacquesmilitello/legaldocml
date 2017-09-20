package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.WRAP;

/**
 * The element wrap is a concluding element in a hierarchy that contains paragraphs wrapping up the preceding lower hierarchical elements.
 *
 * <pre>
 * 	<xsd:element name="wrap" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Wrap extends Blocksopt implements PopupStructureElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_WRAP = Buffers.address(WRAP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_WRAP, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_WRAP, 4);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return WRAP;
    }
}