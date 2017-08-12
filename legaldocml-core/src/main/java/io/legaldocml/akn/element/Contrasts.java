package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element contrasts is a metadata element specifying a reference to a source contrasted by the argument being
 * described.
 *
 * <pre>
 * 	 &lt;xsd:element name="contrasts" type="judicialArgumentType"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Contrasts extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "contrasts";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}