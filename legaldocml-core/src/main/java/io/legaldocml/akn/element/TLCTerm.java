package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

/**
 * The element TLCTerm is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Term.
 * <p/>
 * <pre>
 *  <xsd:element name="TLCTerm" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCTerm extends ReferenceType implements TLC {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "TLCTerm";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}