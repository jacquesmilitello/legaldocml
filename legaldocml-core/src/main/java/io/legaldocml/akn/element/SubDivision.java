package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a hierarchical container called "subdivision" either explicitly or due to the local tradition.
 * <p/>
 * <pre>
 * 	 <xsd:element name="subdivision" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class SubDivision extends Hierarchy implements ANhier {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "subdivision";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 11);
        super.write(writer);
        writer.writeEnd(ADDRESS, 11);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}