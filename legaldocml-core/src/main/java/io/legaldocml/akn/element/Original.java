package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element original is a metadata reference to the Akoma Ntoso IRI of the original version of this document (i.e.,
 * the first expression).
 * <p/>
 * <pre>
 * 	 <xsd:element name="original" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Original extends ReferenceType implements DocRef {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "original";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}