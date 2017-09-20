package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.REFERENCES;

/**
 * The element references is a metadata container of all the references to entities external to the document mentioned
 * in the document. They include references to legal documents of any form,a s well as references to people,
 * organizations, events, roles, concepts, and anything else is managed by the Akoma Ntoso ontology.
 *
 * <pre>
 *   <xsd:element name="references" type="refItems">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class References extends RefItems {

    /**
     * Memory address.
     */
    private static final long ADDRESS_REFERENCES = Buffers.address(REFERENCES);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_REFERENCES, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_REFERENCES, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return REFERENCES;
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