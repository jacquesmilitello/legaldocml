package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.HEADING;

/**
 * the element heading is a heading element in a hierarchy that contains a title or any other textual content to
 * describe the structure.
 *
 * <pre>
 *   <xsd:element name="heading" type="inlinereq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Heading extends InlineReqType implements PopupStructureElement, SubFlowStructureElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_HEADING = Buffers.address(HEADING);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_HEADING, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_HEADING, 7);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return HEADING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }
}