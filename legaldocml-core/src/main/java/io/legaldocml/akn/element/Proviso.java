package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PROVISO;

/**
 * This element is a hierarchical container called "proviso" either explicitly or due to the local tradition.
 *
 * <pre>
 *   <xsd:element name="proviso" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Proviso extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_PROVISO = Buffers.address(PROVISO);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_PROVISO, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_PROVISO, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return PROVISO;
    }

}