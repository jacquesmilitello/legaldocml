package io.legaldocml.akn.element;


import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

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
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRformat";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}