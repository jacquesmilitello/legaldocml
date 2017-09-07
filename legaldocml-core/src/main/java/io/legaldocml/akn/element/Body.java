package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.BODY;

/**
 * The element body is the container of the main hierarchy of a hierarchical document (e.g, an act or a bill).
 *
 * <pre>
 * 	<xsd:element name="body" type="bodyType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Body extends BodyType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_BODY = Buffers.address(BODY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_BODY, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_BODY, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return BODY;
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
