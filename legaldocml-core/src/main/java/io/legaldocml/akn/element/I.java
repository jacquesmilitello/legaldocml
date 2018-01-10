package io.legaldocml.akn.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element i is an HTML element and is used in Akoma Ntoso as in HTML, for the italic style (an inline).
 *
 * <pre>
 * 	 <xsd:element name="i" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class I extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_I = Buffers.address(AknElements.I);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_I, 1);
        super.write(writer);
        writer.writeEnd(ADDRESS_I, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return AknElements.I;
    }


}