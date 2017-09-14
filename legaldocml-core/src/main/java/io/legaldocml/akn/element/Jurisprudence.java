package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.JURISPRUDENCE;

/**
 * The element jurisprudence is a metadata reference to the Akoma Ntoso IRI of a document providing jurisprudence on
 * this document.
 *
 * <pre>
 * 	 <xsd:element name="jurisprudence" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Jurisprudence extends ReferenceType implements DocRef {

    /**
     * Memory address.
     */
    private static final long ADDRESS_JURISPRUDENCE = Buffers.address(JURISPRUDENCE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_JURISPRUDENCE, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_JURISPRUDENCE, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return JURISPRUDENCE;
    }

}