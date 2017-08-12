package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element abbr is an HTML element and is used in Akoma Ntoso as in HTML, for the specification of an abbreviation
 * or an acronym (an inline). As in HTML, use attribute title to specify the full expansion of the abbreviation or
 * acronym.
 *
 * <pre>
 *   &lt;xsd:element name="abbr" type="inline"&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Abbr extends InlineType implements HTMLinline {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT = "abbr";

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