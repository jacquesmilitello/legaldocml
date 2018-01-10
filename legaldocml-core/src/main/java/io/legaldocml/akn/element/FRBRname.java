package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_NAME;

/**
 * The element FRBRname is the metadata property containing a string for the title to be used in the work-level IRI of
 * this document.
 *
 * <pre>
 *   <xsd:element name="FRBRname" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRname extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_NAME = Buffers.address(FRBR_NAME);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_NAME, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_NAME, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}