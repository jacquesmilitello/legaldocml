package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PREVIOUS;

/**
 * The element previous is a metadata element referring to the element (rather than the text) of the modification in the
 * previous version of the document. This is especially useful when renumbering occurs, so as to specify the eId of the
 * instance that was modified in the previous version. CoreAttribute href points to the eId of the element being modified in
 * the old version, using a full expression-level URI.
 *
 * <pre>
 *  <xsd:element name="previous" type="anyOtherType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Previous extends AnyOtherType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PREVIOUS = Buffers.address(PREVIOUS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PREVIOUS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_PREVIOUS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PREVIOUS;
    }

}