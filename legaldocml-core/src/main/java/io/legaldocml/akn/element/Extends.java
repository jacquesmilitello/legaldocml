package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.EXTENDS;

/**
 * The element extends is a metadata element specifying a reference to a source extended by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="extends" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Extends extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_EXTENDS = Buffers.address(EXTENDS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_EXTENDS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_EXTENDS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return EXTENDS;
    }

}