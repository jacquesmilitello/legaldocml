package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element jurisprudence is a metadata reference to the Akoma Ntoso IRI of a document providing jurisprudence on
 * this document.
 *
 * <pre>
 * 	 &lt;xsd:element name="jurisprudence" type="referenceType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Jurisprudence extends ReferenceType implements DocRef {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "jurisprudence";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}