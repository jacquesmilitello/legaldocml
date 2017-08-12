package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element passiveRef is a metadata reference to the Akoma Ntoso IRI of a document providing modifications on this
 * document (i.e., a passive references).
 *
 * <pre>
 * 	 &lt;xsd:element name="passiveRef" type="referenceType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class PassiveRef extends ReferenceType implements DocRef {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "passiveRef";

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