package io.legaldocml.akn.element;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.util.Buffers;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CAPTION;

/**
 * The element caption is an HTML element and is used in Akoma Ntoso as in HTML, for the caption of a table (a block).
 *
 * <pre>
 * 	 <xsd:element name="caption" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Caption extends InlineType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CAPTION = Buffers.address(CAPTION);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CAPTION, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_CAPTION, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CAPTION;
    }

}