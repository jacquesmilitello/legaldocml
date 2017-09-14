package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.LEVEL;

/**
 * This element is a hierarchical container called "level" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="level" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Level extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_LEVEL = Buffers.address(LEVEL);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_LEVEL, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_LEVEL, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return LEVEL;
    }
}