package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.RESTRICTS;

/**
 * The element extends is a metadata element specifying a reference to a source extended by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="restricts" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Restricts extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_RESTRICTS = Buffers.address(RESTRICTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_RESTRICTS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_RESTRICTS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return RESTRICTS;
    }

}