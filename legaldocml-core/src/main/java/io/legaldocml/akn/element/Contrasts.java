package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.CONTRASTS;

/**
 * The element contrasts is a metadata element specifying a reference to a source contrasted by the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="contrasts" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Contrasts extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_CONTRASTS = Buffers.address(CONTRASTS);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_CONTRASTS, 9);
        super.write(writer);
        writer.writeEnd(ADDRESS_CONTRASTS, 9);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return CONTRASTS;
    }

}