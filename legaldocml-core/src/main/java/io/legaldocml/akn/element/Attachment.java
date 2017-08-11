package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element attachment is used as a container of individual attachment elements.
 * <p/>
 * <pre>
 *   <xsd:element name="attachment" type="docContainerType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Attachment extends DocContainerType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "attachment";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

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