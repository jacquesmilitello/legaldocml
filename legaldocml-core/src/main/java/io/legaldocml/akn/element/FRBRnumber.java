package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_NUMBER;

/**
 * The element FRBRnumber is the metadata property containing a string or number for the number to be used in the
 * work-level IRI of this document.
 *
 * <pre>
 *   <xsd:element name="FRBRnumber" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRnumber extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_NUMBER = Buffers.address(FRBR_NUMBER);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_NUMBER, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_NUMBER, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_NUMBER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}