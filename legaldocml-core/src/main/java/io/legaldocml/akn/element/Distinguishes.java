package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element distinguishes is a metadata element specifying a reference to a source being distinguished by the
 * argument being described.
 * <p/>
 * <pre>
 * 	 <xsd:element name="distinguishes" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Distinguishes extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "distinguishes";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}