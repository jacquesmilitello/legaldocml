package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FORCE;

/**
 * The element force is a metadata element specifying the period of the force modification.
 *
 * <pre>
 *   <xsd:element name="force" type="periodType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Force extends PeriodType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FORCE = Buffers.address(FORCE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FORCE, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_FORCE, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FORCE;
    }

}
