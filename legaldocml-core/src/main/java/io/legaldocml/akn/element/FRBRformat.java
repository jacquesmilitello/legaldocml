package io.legaldocml.akn.element;


import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_FORMAT;

/**
 * The element FRBRformat is the metadata property containing the Internet Media Type specification for the data format
 * to be used in the manifestation-level IRI of this document.
 *
 * <pre>
 * 	 <xsd:element name="FRBRformat" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRformat extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_FORMAT = Buffers.address(FRBR_FORMAT);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_FORMAT, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_FORMAT, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_FORMAT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}