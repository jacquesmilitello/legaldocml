package io.legaldocml.akn.element;

import io.legaldocml.akn.CollectionBodyElement;
import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.INTERSTITIAL;

/**
 * The element interstitial is used as a container of text elements and blocks that are placed for any reason between
 * individual documents in a collection of documents.
 *
 * <pre>
 *   <xsd:element name="interstitial" type="blocksreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Interstitial extends Blocksreq implements CollectionBodyElement, DocContainerTypeElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_INTERSTITIAL = Buffers.address(INTERSTITIAL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_INTERSTITIAL, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_INTERSTITIAL, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return INTERSTITIAL;
    }

}