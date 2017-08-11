package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element new is a metadata element containing (in some non-managed form) the new text of the modification.
 * Attribute href points to the eId of the element old it is substituting.
 * <p>
 * <pre>
 *   <xsd:element name="new" type="anyOtherType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class New extends AnyOtherType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "new";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}