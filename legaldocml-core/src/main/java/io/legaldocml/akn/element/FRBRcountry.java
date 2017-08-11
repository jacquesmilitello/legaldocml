package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRcountry is the metadata property containing a ISO 3166-1 Alpha-2 code for the country or jurisdiction
 * to be used in the work-level IRI of this document
 * <p/>
 * <pre>
 * 	 <xsd:element name="FRBRcountry" type="valueType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRcountry extends ValueType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRcountry";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}