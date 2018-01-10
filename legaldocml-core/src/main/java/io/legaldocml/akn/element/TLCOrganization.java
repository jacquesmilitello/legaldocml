package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TLC_ORGANIZATION;

/**
 * The element TLCOrganization is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class
 * Organization.
 *
 * <pre>
 *   <xsd:element name="TLCOrganization" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCOrganization extends ReferenceType implements TLC {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TLC_ORGANIZATION = Buffers.address(TLC_ORGANIZATION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TLC_ORGANIZATION, 15);
        super.write(writer);
        writer.writeEnd(ADDRESS_TLC_ORGANIZATION, 15);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TLC_ORGANIZATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}