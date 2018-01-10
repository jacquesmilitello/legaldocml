package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PASSIVE_REF;

/**
 * The element passiveRef is a metadata reference to the Akoma Ntoso IRI of a document providing modifications on this
 * document (i.e., a passive references).
 *
 * <pre>
 * 	 <xsd:element name="passiveRef" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PassiveRef extends ReferenceType implements DocRef {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PASSIVE_REF = Buffers.address(PASSIVE_REF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PASSIVE_REF, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_PASSIVE_REF, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PASSIVE_REF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}