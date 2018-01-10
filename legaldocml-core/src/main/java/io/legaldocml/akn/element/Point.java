package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.POINT;

/**
 * This element is a hierarchical container called "point" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="point" type="hierarchy"/>
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 *
 */
public final class Point extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_POINT = Buffers.address(POINT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_POINT, 5);
        super.write(writer);
        writer.writeEnd(ADDRESS_POINT, 5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return POINT;
    }
}