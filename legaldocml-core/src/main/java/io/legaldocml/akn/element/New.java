package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.NEW;

/**
 * The element new is a metadata element containing (in some non-managed form) the new text of the modification.
 * Attribute href points to the eId of the element old it is substituting.
 *
 * <pre>
 *   <xsd:element name="new" type="anyOtherType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class New extends AnyOtherType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_NEW = Buffers.address(NEW);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_NEW, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_NEW, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return NEW;
    }

}