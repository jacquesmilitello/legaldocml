package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANinline;
import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.MREF;

/**
 * The element mref is an inline element containing multiple references (each in turn represented by a ref element)
 *
 * <pre>
 *   <xsd:element name="mref" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Mref extends InlineType implements ANinline {

    /**
     * Memory address.
     */
    private static final long ADDRESS_MREF = Buffers.address(MREF);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_MREF, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_MREF, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return MREF;
    }

}