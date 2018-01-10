package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_URI;

/**
 * The element FRBRuri is the metadata property containing the IRI of the whole document.
 *
 *
 * <pre>
 *   <xsd:element name="FRBRuri" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRuri extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_URI = Buffers.address(FRBR_URI);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_URI, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_URI, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_URI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }
}