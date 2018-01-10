package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TLC_TERM;

/**
 * The element TLCTerm is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Term.
 *
 * <pre>
 *  <xsd:element name="TLCTerm" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCTerm extends ReferenceType implements TLC {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TLC_TERM = Buffers.address(TLC_TERM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TLC_TERM, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_TLC_TERM, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TLC_TERM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}