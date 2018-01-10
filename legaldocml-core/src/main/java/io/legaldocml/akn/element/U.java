package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element u is an HTML element and is used in Akoma Ntoso as in HTML, for the underline style (an inline).
 *
 * <pre>
 * 	 <xsd:element name="u" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class U extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_U = Buffers.address(AknElements.U);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_U, 1);
        super.write(writer);
        writer.writeEnd(ADDRESS_U, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AknElements.U;
    }

}