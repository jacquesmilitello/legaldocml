package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SubFlowElements;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.POPUP;

/**
 * <pre>
 * 	<xsd:element name="popup" type="popupStructure"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@Deprecated
public final class Popup extends PopupStructure implements SubFlowElements {

    /**
     * Memory address.
     */
    private static final long ADDRESS_POPUP = Buffers.address(POPUP);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_POPUP, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_POPUP, 5);
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return POPUP;
    }
}