package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.TRANSITIONAL;

/**
 * This element is a hierarchical container called "transitional" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="transitional" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Transitional extends Hierarchy implements ANhier {


    /**
     * Memory address.
     */
    private static final long ADDRESS_TRANSITIONAL = Buffers.address(TRANSITIONAL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_TRANSITIONAL, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_TRANSITIONAL, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return TRANSITIONAL;
    }
}