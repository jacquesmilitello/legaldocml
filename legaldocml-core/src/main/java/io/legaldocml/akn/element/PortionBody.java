package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PORTION_BODY;

/**
 * The element portionBody is the container of a portion of another document.
 *
 * <pre>
 *   <xsd:element name="portionBody" type="portionBodyType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PortionBody extends PortionBodyType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PORTION_BODY = Buffers.address(PORTION_BODY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PORTION_BODY, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_PORTION_BODY, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PORTION_BODY;
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