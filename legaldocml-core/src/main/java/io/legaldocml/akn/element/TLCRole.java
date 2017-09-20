package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TLC_ROLE;

/**
 * The element TLCRole is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Role.
 *
 * <pre>
 *   <xsd:element name="TLCRole" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCRole extends ReferenceType implements TLC {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TLC_ROLE = Buffers.address(TLC_ROLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TLC_ROLE, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_TLC_ROLE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TLC_ROLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}