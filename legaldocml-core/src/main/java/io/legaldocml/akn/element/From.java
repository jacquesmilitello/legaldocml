package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FROM;

/**
 * The element from is a heading element in a debate that contains the name or role or a reference to the person doing
 * the speech.
 *
 * <pre>
 * 	 <xsd:element name="from" type="inline"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class From extends InlineType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_FROM = Buffers.address(FROM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_FROM, 4);
        super.write(writer);
        writer.writeEnd(ADDRESS_FROM, 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return FROM;
    }

}