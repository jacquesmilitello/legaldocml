package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element previous is a metadata element referring to the element (rather than the text) of the modification in the
 * previous version of the document. This is especially useful when renumbering occurs, so as to specify the eId of the
 * instance that was modified in the previous version. Attribute href points to the eId of the element being modified in
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
     * XML tag element name.
     */
    public static final String ELEMENT = "previous";

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