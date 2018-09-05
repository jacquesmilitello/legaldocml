package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.OLD;

/**
 * The element old is a metadata element containing (in some non-managed form) the old text of the modification.
 * CoreAttribute href points to the eId of the element new it is being substituted by.
 *
 * <pre>
 *   <xsd:element name="old" type="anyOtherType"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Old extends AnyOtherType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_OLD = Buffers.address(OLD);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_OLD, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS_OLD, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return OLD;
    }

}