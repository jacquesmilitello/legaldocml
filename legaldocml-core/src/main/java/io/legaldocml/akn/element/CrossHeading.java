package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CROSS_HEADING;

/**
 * the element crossHeading is a heading element that is placed side by side with hierarchical containers.
 *
 * <pre>
 *   <xsd:element name="crossHeading" type="inlinereq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CrossHeading extends InlineReqType implements HierarchyElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(CROSS_HEADING);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CROSS_HEADING;
    }

}