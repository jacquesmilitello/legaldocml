package io.legaldocml.akn.element;

import io.legaldocml.akn.CollectionBodyElement;
import io.legaldocml.akn.DocContainerTypeElement;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element interstitial is used as a container of text elements and blocks that are placed for any reason between
 * individual documents in a collection of documents.
 * <p/>
 * <pre>
 *   <xsd:element name="interstitial" type="blocksreq"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Interstitial extends Blocksreq implements CollectionBodyElement, DocContainerTypeElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "interstitial";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}