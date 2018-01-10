package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TLC_CONCEPT;

/**
 * The element TLCConcept is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Concept.
 *
 * <pre>
 *   <xsd:element name="TLCConcept" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCConcept extends ReferenceType implements TLC {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TLC_CONCEPT = Buffers.address(TLC_CONCEPT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TLC_CONCEPT, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_TLC_CONCEPT, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TLC_CONCEPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}