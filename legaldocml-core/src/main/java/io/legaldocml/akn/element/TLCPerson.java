package io.legaldocml.akn.element;

import io.legaldocml.akn.group.TLC;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element TLCPerson is a metadata reference to the Akoma Ntoso IRI of an ontology instance of the class Person.
 * <p/>
 * <pre>
 *   <xsd:element name="TLCPerson" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCPerson extends ReferenceType implements TLC {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "TLCPerson";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}