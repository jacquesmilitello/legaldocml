package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUB_HEADING;

/**
 * the element subheading is a heading element in a hierarchy that contains a subtitle or any other textual content to
 * further describe the structure..
 *
 * <pre>
 *   <xsd:element name="subheading" type="inlinereq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Subheading extends InlineReqType implements PopupStructureElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUB_HEADING = Buffers.address(SUB_HEADING);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUB_HEADING, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUB_HEADING, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUB_HEADING;
    }
}