package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element TLCLocation is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Location.
 * <p/>
 * <pre>
 * 	 <xsd:element name="TLCLocation" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCLocation extends ReferenceType implements TLC {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "TLCLocation";

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