package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a hierarchical container called "tome" either explicitly or due to the local tradition.
 * <p/>
 * <pre>
 * 	 <xsd:element name="tome" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Tome extends Hierarchy implements ANhier {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "tome";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}