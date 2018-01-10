package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.WRAP_UP;

/**
 * The element wrapUp is a concluding element in a hierarchy that contains paragraphs wrapping up the preceding lower
 * hierarchical elements.
 *
 * <pre>
 *   <xsd:element name="wrap" type="blocksreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class WrapUp extends Blocksreq implements SubFlowStructureElement{

    /**
     * Memory address.
     */
    private static final long ADDRESS_WRAP_UP = Buffers.address(WRAP_UP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_WRAP_UP, 6);
        super.write(writer);
        writer.writeEnd(ADDRESS_WRAP_UP, 6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return WRAP_UP;
    }
}