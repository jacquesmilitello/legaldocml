package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DIVISION;

/**
 * This element is a hierarchical container called "division" either explicitly or due to the local tradition.
 *
 * <pre>
 * 	 <xsd:element name="division" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Division extends Hierarchy implements ANhier {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DIVISION = Buffers.address(DIVISION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DIVISION, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_DIVISION, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DIVISION;
    }

}