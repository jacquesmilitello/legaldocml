package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TLC_EVENT;

/**
 * The element TLCEvent is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Event.
 *
 * <pre>
 *   <xsd:element name="TLCEvent" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCEvent extends ReferenceType implements TLC {

    /**
     * Memory address.
     */
    private static final long ADDRESS_TLC_EVENT = Buffers.address(TLC_EVENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TLC_EVENT, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_TLC_EVENT, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TLC_EVENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}