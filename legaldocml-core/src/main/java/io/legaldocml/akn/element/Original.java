package io.legaldocml.akn.element;

import io.legaldocml.akn.group.DocRef;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.ORIGINAL;

/**
 * The element original is a metadata reference to the Akoma Ntoso IRI of the original version of this document (i.e.,
 * the first expression).
 *
 * <pre>
 * 	 <xsd:element name="original" type="referenceType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Original extends ReferenceType implements DocRef {

    /**
     * Memory address.
     */
    private static final long ADDRESS_ORIGINAL = Buffers.address(ORIGINAL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ORIGINAL, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_ORIGINAL, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ORIGINAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        visitor.visit(this);
    }

}