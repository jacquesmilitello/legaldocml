package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_COUNTRY;

/**
 * The element FRBRcountry is the metadata property containing a ISO 3166-1 Alpha-2 code for the country or jurisdiction
 * to be used in the work-level IRI of this document
 *
 * <pre>
 * 	 <xsd:element name="FRBRcountry" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRcountry extends ValueType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FRBR_COUNTRY = Buffers.address(FRBR_COUNTRY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FRBR_COUNTRY, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS_FRBR_COUNTRY, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FRBR_COUNTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}