package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element caption is an HTML element and is used in Akoma Ntoso as in HTML, for the caption of a table (a block).
 * <p/>
 * <pre>
 * 	 <xsd:element name="caption" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Caption extends InlineType {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "caption";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }

}