package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element references is a metadata container of all the references to entities external to the document mentioned
 * in the document. They include references to legal documents of any form,a s well as references to people,
 * organizations, events, roles, concepts, and anything else is managed by the Akoma Ntoso ontology.
 *
 * <pre>
 *   &lt;xsd:element name="references" type="refItems"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class References extends RefItems {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "references";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
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