package io.legaldocml.akn.element;

import io.legaldocml.akn.group.HTMLinline;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SPAN;

/**
 * The element span is an HTML element and is used in Akoma Ntoso as in HTML, for the generic inline.
 *
 * <pre>
 *   <xsd:element name="span" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Span extends InlineType implements HTMLinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SPAN = Buffers.address(SPAN);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SPAN, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_SPAN, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SPAN;
    }

}