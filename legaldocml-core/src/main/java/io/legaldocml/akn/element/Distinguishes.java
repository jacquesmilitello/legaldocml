package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DISTINGUISHES;

/**
 * The element distinguishes is a metadata element specifying a reference to a source being distinguished by the
 * argument being described.
 *
 * <pre>
 * 	 <xsd:element name="distinguishes" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Distinguishes extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DISTINGUISHES = Buffers.address(DISTINGUISHES);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DISTINGUISHES, 13);
        super.write(writer);
        writer.writeEnd(ADDRESS_DISTINGUISHES, 13);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DISTINGUISHES;
    }

}