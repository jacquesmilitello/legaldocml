package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;


/**
 * <pre>
 * 	<xsd:element name="popup" type="popupStructure"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Popup extends PopupStructure implements SubFlowElements {

	/**
	 * XML tag element name.
	 */
    public static final String ELEMENT = "popup";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS, 5);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return ELEMENT;
    }
}