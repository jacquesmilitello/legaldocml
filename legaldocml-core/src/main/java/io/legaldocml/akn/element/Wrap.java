package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element wrap is a concluding element in a hierarchy that contains paragraphs wrapping up the preceding lower hierarchical elements.
 * <p/>
 * <pre>
 * 	<xsd:element name="wrap" type="blocksopt"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Wrap extends Blocksopt implements PopupStructureElement, SubFlowStructureElement {

	/**
	 * XML tag element name.
	 */
    public static final String ELEMENT = "wrap";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return ELEMENT;
    }
}