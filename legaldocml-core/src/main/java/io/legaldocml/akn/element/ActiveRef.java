package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element activeRef is a metadata reference to the Akoma Ntoso IRI of a document that is modified by this document
 * (i.e., an active references).
 *
 * <pre>
 * 	 &lt;xsd:element name="activeRef" type="referenceType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class ActiveRef extends ReferenceType implements DocRef {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "activeRef";

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