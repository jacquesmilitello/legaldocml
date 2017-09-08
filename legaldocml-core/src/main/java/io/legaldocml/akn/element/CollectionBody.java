package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COLLECTION_BODY;

/**
 * the element collectionBody is the container of a list of other documents (e.g, acts, bills, amendments, etc.)
 * possibly interspersed with interstitial elements with content that does not form an individual document.
 *
 * <pre>
 * 	 <xsd:element name="collectionBody" type="collectionBodyType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CollectionBody extends CollectionBodyType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_COLLECTION_BODY = Buffers.address(COLLECTION_BODY);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_COLLECTION_BODY, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS_COLLECTION_BODY, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return COLLECTION_BODY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}