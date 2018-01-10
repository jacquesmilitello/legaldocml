package io.legaldocml.akn.element;

import io.legaldocml.util.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.IS_ANALOG_TO;

/**
 * The element isAnalogTo is a metadata element specifying a reference to a source analog to the argument being
 * described.
 *
 * <pre>
 * 	 <xsd:element name="isAnalogTo" type="judicialArgumentType">
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class IsAnalogTo extends JudicialArgumentType implements JudicialArgumentsElement {

    /**
     * Memory address.
     */
    private static final long ADDRESS_IS_ANALOG_TO = Buffers.address(IS_ANALOG_TO);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_IS_ANALOG_TO, 10);
        super.write(writer);
        writer.writeEnd(ADDRESS_IS_ANALOG_TO, 10);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return IS_ANALOG_TO;
    }

}