package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.SUPPORTS;

/**
 * The element concept is is an inline element to identify a text fragment introducing or referring to a concept in the
 * ontology.
 *
 * <pre>
 * 	 <xsd:element name="supports" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Supports extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_SUPPORTS = Buffers.address(SUPPORTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_SUPPORTS, 8);
        super.write(writer);
        writer.writeEnd(ADDRESS_SUPPORTS, 8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return SUPPORTS;
    }

}