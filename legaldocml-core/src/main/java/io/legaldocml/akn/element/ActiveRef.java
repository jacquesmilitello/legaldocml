package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ACTIVE_REF;

/**
 * The element activeRef is a metadata reference to the Akoma Ntoso IRI of a document that is modified by this document
 * (i.e., an active references).
 *
 * <pre>
 * 	 <xsd:element name="activeRef" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ActiveRef extends ReferenceType implements DocRef {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ACTIVE_REF = Buffers.address(ACTIVE_REF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ACTIVE_REF, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_ACTIVE_REF, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ACTIVE_REF;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}