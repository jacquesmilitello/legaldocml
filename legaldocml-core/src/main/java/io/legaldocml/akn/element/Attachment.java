package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ATTACHMENT;

/**
 * The element attachment is used as a container of individual attachment elements.
 *
 * <pre>
 *   <xsd:element name="attachment" type="docContainerType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Attachment extends DocContainerType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ATTACHMENT = Buffers.address(ATTACHMENT);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ATTACHMENT, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_ATTACHMENT, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ATTACHMENT;
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