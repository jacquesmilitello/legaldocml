package io.legaldocml.akn.element;

import io.legaldocml.akn.group.SpeechSection;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.POINT_OF_ORDER;

/**
 * This element is a structural container for parts of a debates that are relevant to points of order.
 *
 * <pre>
 * 	 <xsd:element name="pointOfOrder" type="althierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PointOfOrder extends AltHierarchy implements SpeechSection {

    /**
     * Memory address.
     */
    private static final long ADDRESS_POINT_OF_ORDER = Buffers.address(POINT_OF_ORDER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_POINT_OF_ORDER, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_POINT_OF_ORDER, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return POINT_OF_ORDER;
    }

}
