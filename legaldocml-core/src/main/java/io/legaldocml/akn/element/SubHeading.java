package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * the element subheading is a heading element in a hierarchy that contains a subtitle or any other textual content to
 * further describe the structure..
 * <p/>
 * <pre>
 *   <xsd:element name="subheading" type="inlinereq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubHeading extends InlineReqType implements PopupStructureElement, SubFlowStructureElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "subheading";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}