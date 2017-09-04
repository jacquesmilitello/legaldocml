package io.legaldocml.akn.element;

import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.APPLIES;

/**
 * The element applies is a metadata element specifying a reference to a source applyed by the argument being described.
 *
 * <pre>
 * 	 <xsd:element name="applies" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Applies extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(APPLIES);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return APPLIES;
    }

}