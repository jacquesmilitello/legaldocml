package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * the element collectionBody is the container of a list of other documents (e.g, acts, bills, amendments, etc.)
 * possibly interspersed with interstitial elements with content that does not form an individual document.
 *
 * <pre>
 * 	 &lt;xsd:element name="collectionBody" type="collectionBodyType"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class CollectionBody extends CollectionBodyType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "collectionBody";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 14);
        super.write(writer);
        writer.writeEnd(ADDRESS, 14);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
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