package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element b is an HTML element and is used in Akoma Ntoso as in HTML, for the bold style (an inline).
 *
 * <pre>
 *   <xsd:element name="b" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class B extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_B = Buffers.address(AknElements.B);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_B, 1);
        super.write(writer);
        writer.writeEnd(ADDRESS_B, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AknElements.B;
    }

}