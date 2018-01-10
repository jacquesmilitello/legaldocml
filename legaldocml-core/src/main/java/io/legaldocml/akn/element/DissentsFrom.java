package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DISSENTS_FROM;

/**
 * The element dissentsFrom is a metadata element specifying a reference to a source dissented from the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="dissentsFrom" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DissentsFrom extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DISSENTS_FROM = Buffers.address(DISSENTS_FROM);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DISSENTS_FROM, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_DISSENTS_FROM, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DISSENTS_FROM;
    }

}