package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element sup is an HTML element and is used in Akoma Ntoso as in HTML, for the superscript style (an inline).
 * <p/>
 * <pre>
 * 	 <xsd:element name="sup" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Sup extends InlineType implements HTMLinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "sup";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 3);
        super.write(writer);
        writer.writeEnd(ADDRESS, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }


}